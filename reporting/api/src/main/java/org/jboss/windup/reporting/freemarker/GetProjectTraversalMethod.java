package org.jboss.windup.reporting.freemarker;

import java.util.List;

import freemarker.ext.beans.StringModel;
import freemarker.template.SimpleScalar;
import org.jboss.windup.config.GraphRewrite;

import freemarker.template.TemplateModelException;
import org.jboss.windup.graph.model.ProjectModel;
import org.jboss.windup.graph.traversal.AllTraversalStrategy;
import org.jboss.windup.graph.traversal.OnlyOnceTraversalStrategy;
import org.jboss.windup.graph.traversal.ProjectModelTraversal;
import org.jboss.windup.graph.traversal.TraversalStrategy;
import org.jboss.windup.util.ExecutionStatistics;

/**
 * Gets a {@link ProjectModelTraversal} instance for the given {@link ProjectModel}. In order for this to be effective,
 * the project passed in should be a "root" project model.
 *
 * An optional parameter defines the traversal policy.
 * <ul>
 *     <li>getProjectTraversal(project, 'only_once') - Only returns the first instance found of each project during traversal.</li>
 *     <li>getProjectTraversal(project, 'all') - Returns all projects, including all duplicates.</li>
 * </ul>
 *
 * If nothing is specified, then the default is all.
 *
 * @author <a href="mailto:jesse.sightler@gmail.com">Jesse Sightler</a>
 */
public class GetProjectTraversalMethod implements WindupFreeMarkerMethod
{
    public static final String NAME = "getProjectTraversal";
    public static final String ONLY_ONCE = "only_once";
    public static final String ALL = "all";

    @Override
    public String getMethodName()
    {
        return NAME;
    }

    @Override
    public String getDescription()
    {
        return "Gets a ProjectModelTraversal for the given ProjectModel. An optional parameter specifies the traversal " +
                "strategy ('" + ONLY_ONCE + "' or '" + ALL + "').";
    }

    @Override
    public Object exec(List arguments) throws TemplateModelException
    {
        ExecutionStatistics.get().begin(NAME);
        if (arguments.size() < 1)
        {
            throw new TemplateModelException("Error, method expects at least one argument (" + ProjectModel.class.getSimpleName() + ")");
        }
        StringModel stringModelArg = (StringModel) arguments.get(0);
        ProjectModel projectModel = (ProjectModel) stringModelArg.getWrappedObject();

        String traversalStrategyString = ALL;
        if (arguments.size() > 1)
            traversalStrategyString = ((SimpleScalar)arguments.get(1)).getAsString();

        TraversalStrategy traversalStrategy = ONLY_ONCE.equals(traversalStrategyString) ? new OnlyOnceTraversalStrategy() : new AllTraversalStrategy();

        ProjectModelTraversal traversal = new ProjectModelTraversal(projectModel, traversalStrategy);
        ExecutionStatistics.get().end(NAME);
        return traversal;
    }

    @Override
    public void setContext(GraphRewrite event)
    {

    }
}
