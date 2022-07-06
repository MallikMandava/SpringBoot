//package com.intraware.util.template;

import java.io.Reader;
import java.io.FilterReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Reader filter that converts an input stream of a simple template into an XML
 * version of the template. This xml version can then be manipulated using XML
 * methods (i.e. XSLT style sheets).
 * 
 * @author Intraware
 * @version bdevries 2001-12-10 - Created Class.
 * @since 5.2
 */
public class SimpleTemplateFilter extends FilterReader
{
	/**
	 * The size of the internal buffer to use.
	 */
	private static final int BUFFER_SIZE = 256;

	/**
	 * Information about how tags are represented in the template.
	 */
	private static final char[] BEGIN_TAG = "[[".toCharArray();
	private static final char[] END_TAG = "]]".toCharArray();

	private static final List contentTags = Arrays.asList(new String[] {
			"for-each", "if", "ifnull", "choose", "when", "otherwise",
			"elseif", "else" });

	private static final String END_TAG_NAME = "end";

	/**
	 * Information about how tags should be represented in the new version of
	 * the template.
	 */
	private static final String NEW_BEGIN_TAG = "]]><";
	private static final String NEW_END_TAG = "><![CDATA[";

	private static final String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<SimpleTemplate xmlns=\"http://www.intraware.com/schemas/2002/01/SimpleTemplate\">"
			+ "<![CDATA[";

	private static final String FOOTER = "]]></SimpleTemplate>";

	/**
	 * State variables used while converting the template.
	 */
	private StringBuffer buffer = new StringBuffer();

	private int nextChar = 0;
	private int lastChar = 0;

	private boolean footerNeedsAppending = true;

	private boolean inTag;
	private int line = 1;
	private int column = 0;

	private List<String> ignoredTags = Collections.emptyList();

	/**
	 * Event handler to notify of parsing events.
	 */
//	private EventHandler eventHandler;

	/**
	 * Create a new simple-template filter to convert a reader for a simple
	 * template into XML content that can be parsed and manipulated.
	 * 
	 * @param in
	 *            The Reader containing the simple-template content.
	 */
	public SimpleTemplateFilter(Reader in)
	{
		this(in, null);
	}

	/**
	 * Create a new simple-template filter to convert a reader for a simple
	 * template into XML content that can be parsed and manipulated.
	 * 
	 * @param in
	 *            The Reader containing the simple-template content.
	 * @param ignoredTags
	 *            Any tags that should not be replaced
	 */
	public SimpleTemplateFilter(Reader in, String[] ignoredTags)
	{
		super(in);
		buffer.append(HEADER);
		lastChar = HEADER.length();

		if (ignoredTags != null)
		{
			this.ignoredTags = new ArrayList<String>(ignoredTags.length);
			for (int i = 0; i < ignoredTags.length; i++)
			{
				this.ignoredTags.add(ignoredTags[i].toLowerCase());
			}
		}
	}

	/**
	 * Set an event handler which should be notified of parsing events as they
	 * occur during parsing. To remove the event handler pass in a null value.
	 * 
	 * @param eventHandler
	 *            The handler to notify with parsing events or null to remove
	 *            the handler.
	 */
	/*public void setEventHandler(EventHandler eventHandler)
	{
		this.eventHandler = eventHandler;
	}*/

	/**
	 * Close the stream.
	 * 
	 * @exception IOException
	 *                If an I/O error occurs
	 */
	public void close() throws IOException
	{
		synchronized (lock)
		{
			if (in == null)
				return;
			in.close();
			in = null;
			buffer = null;
			
			if (inTag)
				try {
					throw new Exception();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * Tell whether this stream is ready to be read. A buffered character stream
	 * is ready if the buffer is not empty, or if the underlying character
	 * stream is ready.
	 * 
	 * @exception IOException
	 *                If an I/O error occurs
	 */
	public boolean ready() throws IOException
	{
		synchronized (lock)
		{
			ensureOpen();
			return (nextChar < buffer.length()) || in.ready();
		}
	}

	/**
	 * Read a single character. This method will block until a character is
	 * available, an I/O error occurs, or the end of the stream is reached.
	 * 
	 * @returns The character read, as an integer in the range 0 to 65535
	 *          (0x00-0xffff), or -1 if the end of the stream has been reached
	 * @throws IOException
	 *             If an I/O error occurs
	 */
	public int read() throws IOException
	{
		synchronized (lock)
		{
			ensureOpen();
			if (nextChar >= lastChar)
			{
				try {
					fill();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (nextChar >= lastChar)
				{
					if (inTag)
						try {
							throw new Exception();
						} catch (Exception e) {
							e.printStackTrace();
						}
					else
						return -1;
				}
			}
			return buffer.charAt(nextChar++);
		}
	}

	/**
	 * Read characters into a portion of an array. This method will block until
	 * some input is available, an I/O error occurs, or the end of the stream is
	 * reached.
	 * 
	 * @param cbuf
	 *            Destination buffer
	 * @param off
	 *            Offset at which to start storing characters
	 * @param len
	 *            Maximum number of characters to read
	 * @returns The number of characters read, or -1 if the end of the stream
	 *          has beenreached
	 * @throws IOException
	 *             If an I/O error occurs
	 */
	public int read(char[] cbuf, int off, int len) throws IOException
	{
		synchronized (lock)
		{
			ensureOpen();
			if ((off < 0) || (off > cbuf.length) || (len < 0)
					|| ((off + len) > cbuf.length) || ((off + len) < 0))
			{
				throw new IndexOutOfBoundsException();
			}
			else if (len == 0)
			{
				return 0;
			}
			int n = 0;
			try {
				n = read1(cbuf, off, len);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (n <= 0)
				return n;
			while ((n < len) && this.ready())
			{
				int n1 = 0;
				try {
					n1 = read1(cbuf, off + n, len - n);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (n1 <= 0)
					break;
				n += n1;
			}
			return n;
		}
	}

	/**
	 * Read characters into a portion of an array, reading from the underlying
	 * stream if necessary.
	 * 
	 * @param cbuf
	 *            Destination buffer
	 * @param off
	 *            Offset at which to start storing characters
	 * @param len
	 *            Maximum number of characters to read
	 * @returns The number of characters read, or -1 if the end of the stream
	 *          has beenreached
	 * @throws IOException
	 *             If an I/O error occurs
	 */
	private int read1(char[] cbuf, int off, int len) throws Exception
	{
		if (nextChar >= lastChar)
		{
			fill();
		}
		if (nextChar >= lastChar)
		{
			return -1;
		}
		int n = Math.min(len, lastChar - nextChar);
		buffer.getChars(nextChar, nextChar + n, cbuf, off);
		nextChar += n;
		return n;
	}

	/**
	 * Fill the buffer with readable data. If data was left over from the
	 * previous read, it will be moved to the begining of the buffer and the
	 * remaining space will be filled. This fill will also filter the buffer so
	 * that all required conversion is complete after the fill.
	 * 
	 * @throws IOException
	 *             If an I/O error occurs
	 */
	private void fill() throws Exception
	{
		// Copy any old data to the begining of the buffer

		if (lastChar < buffer.length())
		{
			buffer = buffer.delete(0, lastChar);
		}
		else
		{
			buffer.setLength(0);
		}

		// Fill the remaining space in the buffer with data from the
		// underlying data stream.

		char[] cbuf = new char[BUFFER_SIZE - buffer.length()];
		int n = 0;
		while (n == 0)
		{
			n = in.read(cbuf);
		}
		if (n > 0)
		{
			buffer.append(cbuf, 0, n);
		}
		nextChar = 0;
		lastChar = 0;

		// When the underlying stream is done, append the footer to the
		// buffer and mark that is has been appended.

		if (n == -1 && footerNeedsAppending)
		{
			if (inTag)
				throw new Exception();
			
			buffer.append(FOOTER);
			footerNeedsAppending = false;
		}

		filterBuffer();
	}

	/**
	 * Filter the buffer, replacing all old tag syntax with new XML syntax and
	 * marking the portion of the buffer which can be read. This may leave data
	 * in the buffer which has not yet been filtered if only a portion of a tag
	 * that may need filtering is in the buffer at the time of the filter.
	 * 
	 * @throws IOException
	 *             If an I/O error occurs
	 */
	private void filterBuffer() throws Exception
	{	
		int beginTagIndex = 0;
		int endTagIndex = 0;
		
		inTag = false;

		StringBuffer tag = new StringBuffer();

		for (int i = 0; i < buffer.length(); i++)
		{
			char c = buffer.charAt(i);

			if (footerNeedsAppending)
			{
				// Only keep these counters before we are done with the
				// template.
				if ('\n' == c)
				{
					line += 1;
					column = 0;
				}
				else
				{
					column += 1;
				}
			}

			if (!inTag && c == BEGIN_TAG[beginTagIndex])
			{
				tag.append(c);
				beginTagIndex++;
				if (!(beginTagIndex < BEGIN_TAG.length))
				{
					inTag = true;
					beginTagIndex = 0;
				}
			}
			else if (inTag && c == END_TAG[endTagIndex])
			{
				tag.append(c);
				endTagIndex++;
				if (!(endTagIndex < END_TAG.length))
				{
					inTag = false;
					endTagIndex = 0;
					String newTag = convertTag(tag.toString());
					buffer.replace(lastChar, lastChar + tag.length(), newTag);
					lastChar += newTag.length();
					i += newTag.length() - tag.length();
					tag.setLength(0);
				}
			}
			else if (inTag && c == BEGIN_TAG[0])
			{
				throw new Exception();
			}
			else if (inTag)
			{
				tag.append(c);
				beginTagIndex = 0;
				endTagIndex = 0;
			}
			else
			{
				lastChar += tag.length() + 1;
				tag.setLength(0);
				beginTagIndex = 0;
				endTagIndex = 0;
			}
		}
	}

	/**
	 * Converts an old tag into a new tag.
	 * 
	 * @param tag
	 *            The old tag including open and close tag syntax.
	 * @returns The new tag including open and close tag syntax.
	 */
	private String convertTag(String tag)
	{
		String oldTag = tag;

		tag = tag.substring(BEGIN_TAG.length, tag.length() - END_TAG.length);

		StringBuffer tagname = new StringBuffer();
		for (int i = 0; i < tag.length()
				&& !Character.isWhitespace(tag.charAt(i)); i++)
		{
			tagname.append(tag.charAt(i));
		}

		if (ignoredTags.contains(tagname.toString().toLowerCase()))
		{
			return oldTag;
		}

		StringBuffer newTag = new StringBuffer();
		newTag.append(NEW_BEGIN_TAG);

		if (tagname.toString().equalsIgnoreCase(END_TAG_NAME))
		{
			String tagContent = tag.substring(END_TAG_NAME.length() + 1);
			newTag.append("/");
			newTag.append(tagContent);

		}
		else if (!contentTags.contains(tagname.toString().toLowerCase()))
		{
			newTag.append(tag);
			newTag.append("/");

		}
		else
		{
			newTag.append(tag);

		}

		newTag.append(NEW_END_TAG);

		return newTag.toString();
	}

	/**
	 * Ensure that the underlying datastream has not previously been closed.
	 * 
	 * @throws IOException
	 *             If the underlying datastream has been closed.
	 */
	private void ensureOpen() throws IOException
	{
		if (in == null)
			throw new IOException("Stream closed");
	}
}
