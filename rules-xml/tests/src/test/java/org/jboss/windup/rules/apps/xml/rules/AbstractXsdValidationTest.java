package org.jboss.windup.rules.apps.xml.rules;

import org.jboss.windup.graph.GraphContext;
import org.jboss.windup.graph.model.WindupConfigurationModel;
import org.jboss.windup.graph.model.resource.FileModel;
import org.jboss.windup.graph.service.GraphService;
import org.jboss.windup.rules.apps.xml.model.XmlFileModel;

/**
 * Created by mbriskar on 1/11/16.
 */
public class AbstractXsdValidationTest
{
    public static final String VALID_XML = "src/test/resources/xsd-validation/example-pom.xml";
    public static String NOT_VALID_XML = "src/test/resources/xsd-validation/not-xsd-valid.xml";
    public static String URL_NOT_PARSABLE = "src/test/resources/xsd-validation/xsd-url-not-parsable.xml";
    public static final String NOT_VALID_XSD_SCHEMA_URL="src/test/resources/xsd-validation/xsd-url-not-exist.xml";
    public static final String NO_XSD_SCHEMA_URL="src/test/resources/xsd-validation/no-xsd-url.xml";

    protected void addFileModel(GraphContext context, String filePath) {
        FileModel fileModel = context.getFramed().addVertex(null, XmlFileModel.class);
        String fileName = parseFileName(filePath);

        fileModel.setFilePath(filePath);
        fileModel.setFileName(fileName);
    }

    protected String parseFileName(String filePath) {
        String[] filePathSplitted = filePath.split("/");
        String fileName = filePathSplitted[filePathSplitted.length-1];
        return fileName;
    }

    protected void initOnlineWindupConfiguration(GraphContext context)
    {
        initWindupConfiguration(context).setOfflineMode(false);
    }

    protected void initOfflineWindupConfiguration(GraphContext context)
    {
        initWindupConfiguration(context).setOfflineMode(true);
    }

    private WindupConfigurationModel initWindupConfiguration(GraphContext context) {
        GraphService<WindupConfigurationModel> configurationService = new GraphService<>(context,
                    WindupConfigurationModel.class);
        return configurationService.create();
    }
}
