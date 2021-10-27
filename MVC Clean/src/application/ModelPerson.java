package application;

public class ModelPerson {
	private String vorname;
	private String nachname;
	private int alter;
	private int skill;
	
	public ModelPerson(String vorname, String nachname, int alter, int skill) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.alter = alter;
		this.skill = skill;
	}

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public int getAlter() {
		return alter;
	}

	public int getSkill() {
		return skill;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public void setAlter(int alter) {
		this.alter = alter;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}
	
}
