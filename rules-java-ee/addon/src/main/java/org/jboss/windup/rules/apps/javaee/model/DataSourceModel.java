package org.jboss.windup.rules.apps.javaee.model;

import org.jboss.windup.graph.Indexed;

import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.modules.typedgraph.TypeValue;

/**
 * Represents a data source within the application.
 */
@TypeValue(DataSourceModel.TYPE)
public interface DataSourceModel extends JNDIResourceModel
{
    public static final String DATA_SOURCE = "dataSource";
    public static final String TYPE = "DataSourceModel";
    public static final String NAME = "dataSourceName";
    public static final String DATABASE_TYPE_NAME = "databaseTypeName";
    public static final String DATABASE_TYPE_VERSION = "databaseTypeVersion";
    public static final String XA_TYPE = "xaType";
    
    /**
     * Contains persistence unit name
     */
    @Indexed
    @Property(NAME)
    public String getName();

    /**
     * Defines whether it is an XA datasource
     */
    @Property(NAME)
    public void setName(String name);
    
    @Property(XA_TYPE)
    public Boolean getXa();

    /**
     * Defines whether it is an XA datasource
     */
    @Property(XA_TYPE)
    public Boolean setXa(Boolean name);
    
    
    /**
     * Contains database type name 
     */
    @Property(DATABASE_TYPE_NAME)
    String getDatabaseTypeName();
    
    /**
     * Contains database type name 
     */
    @Property(DATABASE_TYPE_NAME)
    void setDatabaseTypeName(String databaseTypeName);
    
    
    /**
     * Contains database type version 
     */
    @Property(DATABASE_TYPE_VERSION)
    String getDatabaseTypeVersion();

    
    /**
     * Contains database type version 
     */
    @Property(DATABASE_TYPE_VERSION)
    void setDatabaseTypeVersion(String databaseTypeVersion);

}
