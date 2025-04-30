package fr.paris.lutece.plugins.workflow.modules.example.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import fr.paris.lutece.plugins.workflow.modules.example.business.Workflow;
import fr.paris.lutece.portal.service.message.SiteMessageException;

public interface IExampleTaskService {
	// SET

    /**
     * Set the site message
     * 
     * @param request
     *            the HTTP request
     * @param strMessage
     *            the message
     * @param nTypeMessage
     *            the message type
     * @param strUrlReturn
     *            the url return
     * @throws SiteMessageException
     *             the site message
     */
    void setSiteMessage( HttpServletRequest request, String strMessage, int nTypeMessage, String strUrlReturn ) throws SiteMessageException;

    // CRUD

    /**
     * Create an reply
     * 
     * @param reply
     */
    @Transactional( WorkflowPlugin.BEAN_TRANSACTION_MANAGER )
    void create( Workflow reply );

    /**
     * Update an reply
     * 
     * @param reply
     */
    @Transactional( WorkflowPlugin.BEAN_TRANSACTION_MANAGER )
    void update( Workflow reply );

    /**
     * Find an reply
     * 
     * @param nIdHistory
     *            the id history
     * @param nIdTask
     *            the id task
     * @return a reply
     */
    Workflow find( int nIdTask );

    /**
     * Find replys by a given id task
     * 
     * @param nIdTask
     *            the id task
     * @return the list of replys
     */
    List<Workflow> findByIdTask( int nIdTask );

    /**
     * Remove an reply by id task
     * 
     * @param nIdTask
     *            the id task
     */
    @Transactional( WorkflowPlugin.BEAN_TRANSACTION_MANAGER )
    void removeByIdTask( int nIdTask );
}
