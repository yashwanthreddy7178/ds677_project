package org.moflon.tie.gt.compiler.democles.codegen.template;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.gervarro.democles.codegen.GeneratorOperation;
import org.gervarro.democles.codegen.ImportManager;
import org.gervarro.democles.codegen.OperationSequenceCompiler;
import org.gervarro.democles.codegen.PatternInvocationConstraintTemplateProvider;
import org.gervarro.democles.codegen.emf.EMFTemplateProvider;
import org.gervarro.democles.codegen.emf.EcoreToGenModelConverter;
import org.gervarro.democles.codegen.stringtemplate.AdornmentHandler;
import org.gervarro.democles.codegen.stringtemplate.FullyQualifiedName;
import org.gervarro.democles.codegen.stringtemplate.ImportHandler;
import org.gervarro.democles.codegen.stringtemplate.StringRenderer;
import org.gervarro.democles.codegen.stringtemplate.emf.EcoreModelAdaptor;
import org.gervarro.democles.codegen.stringtemplate.emf.GenModelAdaptor;
import org.gervarro.democles.common.runtime.VariableRuntime;
import org.gervarro.democles.constraint.emf.EMFVariable;
import org.gervarro.democles.relational.RelationalConstraintTemplateProvider;
import org.gervarro.democles.specification.ConstraintVariable;
import org.moflon.core.utilities.WorkspaceHelper;
import org.moflon.tie.gt.compiler.democles.config.TieGtCodeGenerationConfiguration;
import org.moflon.tie.gt.compiler.democles.searchplan.AssignmentTemplateProvider;
import org.moflon.tie.gt.constraints.operationspecification.AttributeConstraintLibrary;
import org.moflon.tie.gt.constraints.operationspecification.AttributeConstraintsLibraryRegistry;
import org.moflon.tie.gt.constraints.operationspecification.AttributeConstraintsOperationActivator;
import org.moflon.tie.gt.constraints.operationspecification.OperationSpecificationGroup;
import org.moflon.tie.gt.controlflow.democles.CFNode;
import org.moflon.tie.gt.controlflow.democles.CFVariable;
import org.moflon.tie.gt.controlflow.democles.PatternInvocation;
import org.moflon.tie.gt.controlflow.democles.VariableReference;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupString;

public class TieGtTemplateConfiguration implements TemplateConfigurationProvider {
	private static final Logger logger = Logger.getLogger(TieGtTemplateConfiguration.class);

	public static final String CONTROL_FLOW_GENERATOR = "ControlFlowGenerator";

	private final Map<String, STGroup> templates = new HashMap<>();

	private final Map<String, OperationSequenceCompiler> operationSequenceCompilers = new HashMap<>();

	public TieGtTemplateConfiguration(final GenModel genModel,
			final AttributeConstraintsLibraryRegistry attributeConstraintLibraries) {
		final EcoreToGenModelConverter ecoreToGenModelConverter = new EcoreToGenModelConverter(genModel);
		final EcoreModelAdaptor ecoreModelAdaptor = new TiGtEcoreModelAdaptor(ecoreToGenModelConverter);

		final STGroup controlFlowTemplateGroup = createControlFlowTemplates();
		controlFlowTemplateGroup.registerModelAdaptor(EClassifier.class, ecoreModelAdaptor);
		controlFlowTemplateGroup.registerRenderer(EClassifier.class, ecoreModelAdaptor);
		templates.put(CONTROL_FLOW_GENERATOR, controlFlowTemplateGroup);

		createBindingAndBlackTemplates(ecoreModelAdaptor);
		createBindingTemplates(ecoreModelAdaptor);
		createBlackTemplates(ecoreModelAdaptor, attributeConstraintLibraries);
		createRedTemplates(ecoreModelAdaptor);
		createGreenTemplates(ecoreModelAdaptor);
		createExpressionTemplates(ecoreModelAdaptor);

	}

	@Override
	public final STGroup getTemplateGroup(final String id) {
		return templates.get(id);
	}

	@Override
	public final OperationSequenceCompiler getOperationSequenceCompiler(final String id) {
		return operationSequenceCompilers.get(id);
	}

	private static final STGroup createControlFlowTemplates() {
		final STGroup group = new STGroup();
		registerTieGtCommonTemplates(group);
		registerErrorLogger(group);
		group.loadGroupFile(String.format("/%s/", CONTROL_FLOW_GENERATOR),
				String.format("%stemplates/stringtemplate/ControlFlow.stg", getCompilerURI()));

		registerControlFlowModelAdaptor(group);
		registerImportModelAdaptor(group);
		return group;
	}

	private STGroup createPatternTemplates(final EcoreModelAdaptor ecoreModelAdaptor) {
		final STGroup group = new STGroup();
		registerErrorLogger(group);
		registerTieGtCommonTemplates(group);
		registerDemoclesCommonTemplates(group);
		registerRegularTemplates(group);
		registerConstantTemplates(group);
		registerNumberTemplates(group);
		registerRelationOperationTemplates(group);
		registerExpressionTemplates(group);
		registerEmfOperationTemplates(group);
		registerPatternCallTemplates(group);
		registerPrioritizedPatternCallTemplates(group);
		registerEmfDeletionTemplates(group);
		registerEmfCreationTemplates(group);
		registerAssignmentTemplates(group);

		registerGenModelAdaptor(group);
		registerEcoreModelAdaptor(ecoreModelAdaptor, group);
		registerParameterModelAdaptor(group);
		registerAdornmentModelAdaptor(group);
		registerStringModelAdaptor(group);
		registerImportModelAdaptor(group);

		return group;
	}

	private static void registerEcoreModelAdaptor(final EcoreModelAdaptor ecoreModelAdaptor, final STGroup group) {
		group.registerModelAdaptor(EModelElement.class, ecoreModelAdaptor);
		group.registerModelAdaptor(EMFVariable.class, ecoreModelAdaptor);
		group.registerRenderer(EMFVariable.class, ecoreModelAdaptor);
		group.registerRenderer(EClassifier.class, ecoreModelAdaptor);
	}

	@SuppressWarnings("unchecked")
	private static OperationSequenceCompiler createBlackOperationSequenceCompiler() {
		return new OperationSequenceCompiler(new PatternInvocationConstraintTemplateProvider(),
				new RelationalConstraintTemplateProvider(), new EMFTemplateProvider(),
				new AttributeConstraintsTemplateProvider());
	}

	@SuppressWarnings("unchecked")
	private static final OperationSequenceCompiler createRedOperationSequenceCompiler() {
		return new OperationSequenceCompiler(new EMFRedTemplateProvider());
	}

	@SuppressWarnings("unchecked")
	private static final OperationSequenceCompiler createGreenOperationSequenceCompiler() {
		return new OperationSequenceCompiler(new AttributeAssignmentTemplateProvider(), new EMFGreenTemplateProvider());
	}

	@SuppressWarnings("unchecked")
	private static final OperationSequenceCompiler createBindingOperationSequenceCompiler() {
		return new OperationSequenceCompiler(new AssignmentTemplateProvider(), new EMFTemplateProvider());
	}

	@SuppressWarnings("unchecked")
	private static final OperationSequenceCompiler createBindingAndBlackOperationSequenceCompiler() {
		return new OperationSequenceCompiler(new BindingAndBlackTemplateProvider(),
				new PatternInvocationConstraintTemplateProvider(), new RelationalConstraintTemplateProvider(),
				new EMFTemplateProvider(), new AttributeConstraintsTemplateProvider());
	}

	@SuppressWarnings("unchecked")
	private static final OperationSequenceCompiler createExpressionOperationSequenceCompiler() {
		return new OperationSequenceCompiler(new AssignmentTemplateProvider(), new EMFTemplateProvider());
	}

	private final STGroup createBindingAndBlackTemplates(final EcoreModelAdaptor ecoreModelAdaptor) {
		final STGroup group = createPatternTemplates(ecoreModelAdaptor);

		templates.put(TieGtCodeGenerationConfiguration.BINDING_AND_BLACK_PATTERN_MATCHER_GENERATOR, group);

		operationSequenceCompilers.put(TieGtCodeGenerationConfiguration.BINDING_AND_BLACK_PATTERN_MATCHER_GENERATOR,
				createBindingAndBlackOperationSequenceCompiler());

		return group;
	}

	private static void registerPrioritizedPatternCallTemplates(final STGroup group) {
		group.loadGroupFile("/priority/", getCompilerURI() + "templates/stringtemplate/PrioritizedPatternCall.stg");
	}

	private final STGroup createBindingTemplates(final EcoreModelAdaptor ecoreModelAdaptor) {
		final STGroup group = createPatternTemplates(ecoreModelAdaptor);

		templates.put(TieGtCodeGenerationConfiguration.BINDING_PATTERN_MATCHER_GENERATOR, group);

		operationSequenceCompilers.put(TieGtCodeGenerationConfiguration.BINDING_PATTERN_MATCHER_GENERATOR,
				createBindingOperationSequenceCompiler());

		return group;
	}

	private final STGroup createBlackTemplates(final EcoreModelAdaptor ecoreModelAdaptor,
			final AttributeConstraintsLibraryRegistry attributeConstraintLibraries) {
		final STGroup group = createPatternTemplates(ecoreModelAdaptor);

		templates.put(TieGtCodeGenerationConfiguration.BLACK_PATTERN_MATCHER_GENERATOR, group);
		addAttributeConstraintTemplates(group, attributeConstraintLibraries);

		operationSequenceCompilers.put(TieGtCodeGenerationConfiguration.BLACK_PATTERN_MATCHER_GENERATOR,
				createBlackOperationSequenceCompiler());
		return group;
	}

	private static void registerRelationOperationTemplates(final STGroup group) {
		group.loadGroupFile("/core/", getDemoclesCoreURI() + "templates/stringtemplate/RelationalOperation.stg");
	}

	private static void registerPatternCallTemplates(final STGroup group) {
		group.loadGroupFile("/pattern/", getDemoclesCoreURI() + "templates/stringtemplate/PatternCallOperation.stg");
	}

	private final STGroup createRedTemplates(final EcoreModelAdaptor ecoreModelAdaptor) {
		final STGroup group = createPatternTemplates(ecoreModelAdaptor);

		templates.put(TieGtCodeGenerationConfiguration.RED_PATTERN_MATCHER_GENERATOR, group);

		operationSequenceCompilers.put(TieGtCodeGenerationConfiguration.RED_PATTERN_MATCHER_GENERATOR,
				createRedOperationSequenceCompiler());
		return group;
	}

	private static void registerEmfDeletionTemplates(final STGroup group) {
		group.loadGroupFile("/emf-delete/", getCompilerURI() + "templates/stringtemplate/EMFDeleteOperation.stg");
	}

	private final STGroup createGreenTemplates(final EcoreModelAdaptor ecoreModelAdaptor) {
		final STGroup group = createPatternTemplates(ecoreModelAdaptor);

		templates.put(TieGtCodeGenerationConfiguration.GREEN_PATTERN_MATCHER_GENERATOR, group);

		operationSequenceCompilers.put(TieGtCodeGenerationConfiguration.GREEN_PATTERN_MATCHER_GENERATOR,
				createGreenOperationSequenceCompiler());
		return group;
	}

	private static void registerGenModelAdaptor(final STGroup group) {
		group.registerModelAdaptor(GenBase.class, new GenModelAdaptor());
	}

	private final STGroup createExpressionTemplates(final EcoreModelAdaptor ecoreModelAdaptor) {
		final STGroup group = createPatternTemplates(ecoreModelAdaptor);

		templates.put(TieGtCodeGenerationConfiguration.EXPRESSION_PATTERN_MATCHER_GENERATOR, group);

		operationSequenceCompilers.put(TieGtCodeGenerationConfiguration.EXPRESSION_PATTERN_MATCHER_GENERATOR,
				createExpressionOperationSequenceCompiler());
		return group;
	}

	private static void registerAdornmentModelAdaptor(final STGroup group) {
		group.registerModelAdaptor(Integer.class, new AdornmentHandler());
	}

	private static void registerStringModelAdaptor(final STGroup group) {
		group.registerRenderer(String.class, new StringRenderer());
	}

	private static void registerControlFlowModelAdaptor(final STGroup group) {
		final ControlFlowModelAdaptor adaptor = new ControlFlowModelAdaptor();
		group.registerModelAdaptor(PatternInvocation.class, adaptor);
		group.registerModelAdaptor(VariableReference.class, adaptor);
		group.registerModelAdaptor(CFNode.class, adaptor);
		group.registerModelAdaptor(CFVariable.class, adaptor);
	}

	private static void registerParameterModelAdaptor(final STGroup group) {
		final PatternMatcherModelAdaptor parameterRenderer = new PatternMatcherModelAdaptor();
		group.registerModelAdaptor(GeneratorOperation.class, parameterRenderer);
		group.registerModelAdaptor(ConstraintVariable.class, parameterRenderer);
		group.registerModelAdaptor(VariableRuntime.class, parameterRenderer);
	}

	private static void registerImportModelAdaptor(final STGroup group) {
		final ImportHandler importRenderer = new ImportHandler();
		group.registerModelAdaptor(ImportManager.class, importRenderer);
		group.registerModelAdaptor(FullyQualifiedName.class, importRenderer);
	}

	private static void registerNumberTemplates(final STGroup group) {
		group.loadGroupFile("/democles/", getCompilerURI() + "templates/stringtemplate/Number.stg");
	}

	private static void registerErrorLogger(final STGroup group) {
		group.setListener(new LoggingSTErrorListener(logger));
	}

	private static void registerConstantTemplates(final STGroup group) {
		group.loadGroupFile("/democles/", getDemoclesEMFURI() + "templates/stringtemplate/EMFConstant.stg");
	}

	private static void registerTieGtCommonTemplates(final STGroup group) {
		group.loadGroupFile("/tiegt/", getCompilerURI() + "templates/stringtemplate/TieGtCommon.stg");
	}

	private static void registerEmfOperationTemplates(final STGroup group) {
		group.loadGroupFile("/emf/", getCompilerURI() + "templates/stringtemplate/EMFOperation.stg");
	}

	private static void registerEmfCreationTemplates(final STGroup group) {
		group.loadGroupFile("/emf-create/", getCompilerURI() + "templates/stringtemplate/EMFCreateOperation.stg");
	}

	private static void registerDemoclesCommonTemplates(final STGroup group) {
		group.loadGroupFile("/democles/", getDemoclesCoreURI() + "templates/stringtemplate/DemoclesCommon.stg");
	}

	private static void registerRegularTemplates(final STGroup group) {
		group.loadGroupFile("/regular/", getCompilerURI() + "templates/stringtemplate/RegularPatternMatcher.stg");
	}

	private static void registerAssignmentTemplates(final STGroup group) {
		group.loadGroupFile("/assignment/", getCompilerURI() + "templates/stringtemplate/Assignment.stg");
	}

	private static void registerExpressionTemplates(final STGroup group) {
		group.loadGroupFile("/expression/", getCompilerURI() + "templates/stringtemplate/ExpressionPatternMatcher.stg");
	}

	private static String getCompilerURI() {
		final String pluginId = WorkspaceHelper.getPluginId(TieGtTemplateConfiguration.class);
		return "platform:/plugin/" + pluginId + "/";
	}

	private static String getDemoclesCoreURI() {
		return "platform:/plugin/org.gervarro.democles.codegen.stringtemplate/";
	}

	private static String getDemoclesEMFURI() {
		return "platform:/plugin/org.gervarro.democles.codegen.emf/";
	}

	/**
	 * Adds the templates for user-defined constraints to the template group for
	 * black patterns (i.e., patterns that represent preserved variables).
	 * 
	 * @param group                        the template group at which the libraries
	 *                                     shall be registered
	 *
	 * @param attributeConstraintLibraries the library containing user-defined
	 *                                     attribute constraints and operations
	 */
	private void addAttributeConstraintTemplates(final STGroup group,
			final AttributeConstraintsLibraryRegistry attributeConstraintLibraries) {
		for (final URI uri : attributeConstraintLibraries.getUris()) {
			final AttributeConstraintLibrary library = attributeConstraintLibraries.getLibrary(uri);
			for (final OperationSpecificationGroup operationSpecificationGroup : library.getOperationSpecifications()) {

				if (!operationSpecificationGroup.isTemplateGroupGenerated()) {
					operationSpecificationGroup.gernerateTemplate();
				}

				final STGroup newGroup = new STGroupString("someName",
						operationSpecificationGroup.getTemplateGroupString(),
						AttributeConstraintsOperationActivator.OUTER_ST_DELIMITER,
						AttributeConstraintsOperationActivator.OUTER_ST_DELIMITER);

				for (final String templateName : newGroup.getTemplateNames()) {
					final ST template = newGroup.getInstanceOf(templateName);
					final String fullyQualifiedTemplateName = "/" + library.getPrefix() + "/"
							+ operationSpecificationGroup.getOperationIdentifier() + template.getName();
					group.rawDefineTemplate(fullyQualifiedTemplateName, template.impl, null);
				}

			}

		}

	}
}
