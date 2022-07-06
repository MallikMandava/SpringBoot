import org.xml.sax.InputSource;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;



public class sample {

    private  static final String TEMPLATE_STYLESHEET = "SimpleTemplate.xsl";
    static float total=0;
    private Templates templates = null;
    public  void main1() throws TransformerException {


       TransformerFactory tFactory = TransformerFactory.newInstance();
      //  TransformerFactory tFactory = TransformerFactory.newInstance("org.apache.xalan.processor.TransformerFactoryImpl", null);

        if (tFactory.getFeature(SAXSource.FEATURE) && tFactory.getFeature(SAXResult.FEATURE)) {

            long start = System.currentTimeMillis();
            Transformer trans = tFactory.newTransformer(new StreamSource(this.getClass().getResourceAsStream(TEMPLATE_STYLESHEET)));

            long end = System.currentTimeMillis();
            float sec = (end - start) / 1000F;
            total  = total +sec;
            System.out.println(total + "Execution Time in seconds");
            System.out.println("Executing the Code");
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            String template = "VENDOR_STRING";
            final Reader reader = new StringReader(template);
            final SimpleTemplateFilter filter = new SimpleTemplateFilter(reader);
            final InputSource source = new InputSource(filter);



            trans.transform(new SAXSource(source), new StreamResult(output));
            byte[] result = output.toByteArray();
            ByteArrayInputStream input = new ByteArrayInputStream(result);
            ByteArrayInputStream debuginput = new ByteArrayInputStream(result);
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(debuginput));
            try{
                String nextLine = "";
                while((nextLine = reader1.readLine()) != null) {
                    System.out.println(nextLine);
                }
            } catch(IOException e){
                e.printStackTrace();
            }

            templates = tFactory.newTemplates(new StreamSource(input));

        }


    }

    public static void main(String[] args) throws TransformerException {
        sample sm = new sample();
        sm.main1();
    }

    private synchronized Templates getTemplates(String xslPath) throws Exception {
        Templates templates = templatesCache.get(xslPath);
        if (templates == null) {
            TransformerFactory factory = TransformerFactory.newInstance();
            templates = factory.newTemplates(new StreamSource(xslPath));
            templatesCache.put(xslPath, templates);
        }
        return templates;
    }
}
