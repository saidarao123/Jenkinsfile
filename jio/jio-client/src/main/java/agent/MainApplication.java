package agent;

import java.io.File;

import javax.rad.application.ILauncher;
import javax.rad.application.genui.UILauncher;
import javax.rad.genui.UIDimension;
import javax.rad.genui.UIImage;
import javax.rad.genui.component.UIButton;
import javax.rad.genui.container.UIToolBar;
import javax.rad.genui.menu.UIMenu;
import javax.rad.genui.menu.UIMenuItem;
import javax.rad.remote.IConnection;

import agent.screens.EditWorkScreen;

import com.sibvisions.rad.IPackageSetup;
import com.sibvisions.rad.application.Application;
import com.sibvisions.rad.remote.http.HttpConnection;
import com.sibvisions.rad.server.DirectServerConnection;
import com.sibvisions.rad.ui.swing.impl.SwingApplication;
import com.sibvisions.util.ArrayUtil;

/**
 * The application class.
 */
public class MainApplication extends Application
{
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Startup
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Starts the application as standard desktop application.
	 * 
	 * @param pArgs additional arguments
	 */
	public static void main(String[] pArgs)
	{
		String[] sArgs = new String[] {MainApplication.class.getName()};
		sArgs = ArrayUtil.addAll(sArgs, pArgs);

		System.setProperty(IPackageSetup.CONFIG_BASEDIR, new File(new File("../jio-war/").getAbsolutePath(), "/src/main/webapp/WEB-INF").getAbsolutePath());
		
		SwingApplication.main(sArgs);
	}	
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Initialization
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Creates a new application instance.
	 * 
	 * @param pLauncher the technology dependent launcher
	 */
	public MainApplication(UILauncher pLauncher)
	{
		super(pLauncher);
		
		setPreferredSize(new UIDimension(1024, 768));
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Overwritten methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IConnection createConnection() throws Exception
	{
        //TODO replace following code with your connection code		
		
		String sConClass = getLauncher().getParameter("Application.connectionClass");
		
		if (sConClass != null && HttpConnection.class.getName().equals(sConClass))
		{
			String sServerBase = getLauncher().getParameter(ILauncher.PARAM_SERVERBASE);
			
			if (sServerBase == null)
			{
				sServerBase = "http://localhost/jio";
			}
			
			return new HttpConnection(sServerBase + "/services/Server");
		}
		
		//client + server in one JVM
		return new DirectServerConnection();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getApplicationName()
	{
		return "jio";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void afterLogin()
	{
		super.afterLogin();

        //TODO replace following code with your application code
		
		//configure MenuBar
		
		UIMenu menuMasterData = new UIMenu();
		menuMasterData.setText("Master data");
		
		UIMenuItem miDBEdit = createMenuItem
		                      ("doOpenEditScreen", null, "Edit", 
		                       UIImage.getImage(UIImage.REGISTER_SMALL));

		menuMasterData.add(miDBEdit);

		//insert before Help
		getMenuBar().add(menuMasterData, 1);
		
		//configure ToolBar

		UIToolBar tbMasterData = new UIToolBar();
		
		UIButton butDBEdit = createToolBarButton
		                     ("doOpenEditScreen", null, "Edit", 
		                      UIImage.getImage(UIImage.REGISTER_LARGE));
		
		tbMasterData.add(butDBEdit);
		
		getLauncher().addToolBar(tbMasterData);
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Actions
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Opens the edit screen.
	 * <p/>
	 * @throws Throwable if the edit frame can not be opened
	 */
	public void doOpenEditScreen() throws Throwable
	{
		EditWorkScreen screen = new EditWorkScreen(this);
		
		configureFrame(screen);
		
		screen.setVisible(true);
	}
	
}	// MainApplication
