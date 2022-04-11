package agent.screens;

import java.math.BigDecimal;

import javax.rad.model.ColumnDefinition;
import javax.rad.model.ColumnView;
import javax.rad.model.ModelException;
import javax.rad.model.RowDefinition;
import javax.rad.model.condition.ICondition;
import javax.rad.model.datatype.BigDecimalDataType;
import javax.rad.model.datatype.TimestampDataType;
import javax.rad.model.event.DataBookEvent;
import javax.rad.persist.IStorage;

import agent.Session;

import com.sibvisions.rad.model.mem.MemDataBook;
import com.sibvisions.rad.persist.AbstractMemStorage;

/**
 * The LCO for {@link} agent.client.screens.EditWorkScreen}.
 */
public class Edit extends Session
{
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // User-defined methods
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	

	/**
	 * Gets the contacts storage.<br>
	 * 
	 * @return the storage
	 * @throws Exception if the storage couldn't initialized.
	 */
	public IStorage getContacts() throws Exception
	{
		//TODO replace example storage with your storage, e.g. DBStorage
		
		//use Session for caching
		AbstractMemStorage amsContacts = (AbstractMemStorage)getParent().get("contacts");
		
		if (amsContacts == null)
		{
			amsContacts = new AbstractMemStorage()
			{
				private int iId = 0;
				
				@Override
				public RowDefinition getRowDefinition() throws ModelException
				{
					RowDefinition rowdef = new RowDefinition();
					rowdef.addColumnDefinition(new ColumnDefinition("ID", new BigDecimalDataType()));
					rowdef.addColumnDefinition(new ColumnDefinition("FIRST_NAME"));
					rowdef.addColumnDefinition(new ColumnDefinition("LAST_NAME"));
					rowdef.addColumnDefinition(new ColumnDefinition("DOB", new TimestampDataType()));
					
					rowdef.setColumnView(null, new ColumnView("ID", "FIRST_NAME", "LAST_NAME", "DOB"));
					
					rowdef.setPrimaryKeyColumnNames(new String[] {"ID"});
					
					return rowdef;
				}
				
				@Override
				public void update(DataBookEvent pEvent) throws ModelException
				{
				}
				
				@Override
				public void loadData(MemDataBook pBook, ICondition pFilter) throws ModelException
				{
				}
				
				@Override
				public void insert(DataBookEvent pEvent) throws ModelException
				{
					pEvent.getChangedDataBook().setValue("ID", BigDecimal.valueOf(iId++));
				}
				
				@Override
				public void delete(DataBookEvent pEvent) throws ModelException
				{
				}
			};

			amsContacts.open();

			//use Session for caching
			getParent().put("contacts", amsContacts);
		}
		
		return amsContacts;
	}

} 	// Edit
