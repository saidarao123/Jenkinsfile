package agent.screens;

import javax.rad.genui.UIDimension;
import javax.rad.genui.container.UIGroupPanel;
import javax.rad.genui.container.UIInternalFrame;
import javax.rad.genui.control.UITable;
import javax.rad.genui.layout.UIBorderLayout;
import javax.rad.remote.AbstractConnection;
import javax.rad.remote.MasterConnection;

import com.sibvisions.rad.application.Application;
import com.sibvisions.rad.model.remote.RemoteDataBook;
import com.sibvisions.rad.model.remote.RemoteDataSource;

/**
 * A simple edit work-screen.
 */
public class EditWorkScreen extends UIInternalFrame
{
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Class members
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/** the application. */
	private Application application;
	
	/** the communication connection to the server. */
	private AbstractConnection connection;
	
	/** the DataSource for fetching table data. */
	private RemoteDataSource dataSource = new RemoteDataSource();
	
	/** the contacts tabl. */
	private RemoteDataBook rdbContacts = new RemoteDataBook();
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Initialization
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a new instance of <code>EditWorkScreen</code> for a specific application.
	 * <p/>
	 * @param pApp the application
	 * @throws Throwable if the remote access fails
	 */
	public EditWorkScreen(Application pApp) throws Throwable
	{
		super(pApp.getDesktopPane());
		
		application = pApp;
		
		initializeModel();
		initializeUI();
	}
	
	/**
	 * Initializes the model.
	 * <p/>
	 * @throws Throwable if the initialization throws an error
	 */
	private void initializeModel() throws Throwable
	{
		//we use a new "session" for the screen
		connection = ((MasterConnection)application.getConnection()).createSubConnection("agent.screens.Edit");
		connection.open();

		//data connection
		dataSource.setConnection(connection);
		dataSource.open();
		
		//table
		rdbContacts.setDataSource(dataSource);
		rdbContacts.setName("contacts");
		rdbContacts.open();
	}
	
	/**
	 * Initializes the UI.
	 * <p/>
	 * @throws Exception if the initialization throws an error
	 */
	private void initializeUI() throws Exception
	{
		UIGroupPanel group = new UIGroupPanel();
		group.setText("Available Contacts");
		
		UITable table = new UITable();
		table.setDataBook(rdbContacts);
		
		group.setLayout(new UIBorderLayout());
		group.add(table);
		
		setLayout(new UIBorderLayout());
		add(group);
		
		setTitle("Contacts");
		setSize(new UIDimension(600, 500));
	}	
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Overwritten methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Closes the communication connection and disposes the frame.
	 */
	@Override
	public void dispose()
	{
		try
		{
			dataSource.saveAllDataBooks();
		}
		catch (Exception e)
		{
			//nothing to be done
		}

		try
		{
			connection.close();
		}
		catch (Throwable th)
		{
			//nothing to be done
		}
		finally
		{
			super.dispose();
		}
	}
	
}	// EditWorkScreen
