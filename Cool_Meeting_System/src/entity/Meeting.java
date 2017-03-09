package entity;

import java.sql.Date;

public class Meeting {
	private int meetingid;
	private String meetingname;
	private MeetingRoom meetingroom;
	private int reservationist_id;
	private int numberofparticipants;
	private Date starttime;
	private Date endtime;
	private Date reservationtime;
	private Date canceledtime;
	private String description;
	private byte status;
	private Employee booker;

	public Meeting() {
		// TODO Auto-generated constructor stub
	}

	public Meeting(int meetingid, String meetingname, MeetingRoom meetingroom, Employee employee,
			int numberofparticipants, Date starttime, Date endtime, Date reservationtime, Date canceledtime,
			String description, byte status, int reservationist_id, Employee booker) {
		// TODO Auto-generated constructor stub
		this.setMeetingid(meetingid);
		this.setMeetingname(meetingname);
		this.setMeetingroom(meetingroom);
		this.setNumberofparticipants(numberofparticipants);
		this.setStarttime(starttime);
		this.setEndtime(endtime);
		this.setReservationtime(reservationtime);
		this.setCanceledtime(canceledtime);
		this.setDescription(description);
		this.setStatus(status);
		this.setReservationist_id(reservationist_id);
		this.setBooker(booker);
	}

	public int getMeetingid() {
		return meetingid;
	}

	public void setMeetingid(int meetingid) {
		this.meetingid = meetingid;
	}

	public String getMeetingname() {
		return meetingname;
	}

	public void setMeetingname(String meetingname) {
		this.meetingname = meetingname;
	}

	public int getNumberofparticipants() {
		return numberofparticipants;
	}

	public void setNumberofparticipants(int numberofparticipants) {
		this.numberofparticipants = numberofparticipants;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Date getReservationtime() {
		return reservationtime;
	}

	public void setReservationtime(Date reservationtime) {
		this.reservationtime = reservationtime;
	}

	public Date getCanceledtime() {
		return canceledtime;
	}

	public void setCanceledtime(Date canceledtime) {
		this.canceledtime = canceledtime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public MeetingRoom getMeetingroom() {
		return meetingroom;
	}

	public void setMeetingroom(MeetingRoom meetingroom) {
		this.meetingroom = meetingroom;
	}

	public Employee getBooker() {
		return booker;
	}

	public void setBooker(Employee booker) {
		this.booker = booker;
	}

	public int getReservationist_id() {
		return reservationist_id;
	}

	public void setReservationist_id(int reservationist_id) {
		this.reservationist_id = reservationist_id;
	}

}
