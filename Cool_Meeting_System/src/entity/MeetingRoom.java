package entity;

public class MeetingRoom {
	private int roomID;
	private int roomNum;
	private String roomName;
	private int capacity;
	private String sta;
	private String description;
	
	public MeetingRoom() {
		// TODO Auto-generated constructor stub
	}
	
	public MeetingRoom(int roomID,int roomNum,String roomName,int capacity,String sta,String description) {
		// TODO Auto-generated constructor stub
		this.setRoomID(roomID);
		this.setRoomNum(roomNum);
		this.setRoomName(roomName);
		this.setCapacity(capacity);
		this.setSta(sta);
		this.setdescription(description);
		
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getSta() {
		return sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	public String getdescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description = description;
	}
	
	
	
}
