package com.pff;

//import java.util.Date;
import java.util.HashMap;

/**
 * Class containing recipient information
 * @author Orin Eman
 * 
 * 
 */
public class PSTRecipient {
	private HashMap<Integer, PSTTable7CItem> details;
	
	PSTRecipient(HashMap<Integer, PSTTable7CItem> recipientDetails)
	{
		details = recipientDetails;
	}
	
	public String getDisplayName() {
		return getString(0x3001);
	}
	
	public int getRecipientType() {
		return getInt(0x0c15);
	}
	
	public String getEmailAddressType() {
		return getString(0x3002);
	}
	
	public String getEmailAddress() {
		return getString(0x3003);
	}
	
	public int getRecipientFlags() {
		return getInt(0x5ffd);
	}
	
	public int getRecipientOrder() {
		return getInt(0x5fdf);
	}
	
	public String getSmtpAddress()
	{
		return getString(0x39FE);
	}
	
	private String getString(int id) {
		if ( details.containsKey(id) ) {
			PSTTable7CItem item = details.get(id);
			return item.getStringValue();
		}
		
		return "";
	}
	
/*	private boolean getBoolean(int id) {
		if ( details.containsKey(id) ) {
			PSTTable7CItem item = details.get(id);
			if ( item.entryValueType == 0x000B )
			{
				return (item.entryValueReference & 0xFF) == 0 ? false : true;
			}
		}
		
		return false;
	}
*/
	private int getInt(int id) {
		if ( details.containsKey(id) ) {
			PSTTable7CItem item = details.get(id);
			if ( item.entryValueType == 0x0003 )
			{
				return item.entryValueReference;
			}

			if ( item.entryValueType == 0x0002 ) {
				short s = (short)item.entryValueReference;
				return (int)s;
			}
		}
		
		return 0;
	}
	
/*	private Date getDate(int id) {
		long lDate = 0;
		
		if ( details.containsKey(id) ) {
			PSTTable7CItem item = details.get(id);
			if ( item.entryValueType == 0x0040 ) {
				int high = (int)PSTObject.convertLittleEndianBytesToLong(item.data, 4, 8);
				int low = (int)PSTObject.convertLittleEndianBytesToLong(item.data, 0, 4);
				 
				return PSTObject.filetimeToDate(high, low);
			}
		}
		return new Date(lDate);
	}
*/
}
