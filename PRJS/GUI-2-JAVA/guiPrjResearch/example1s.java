http://www.gamersonlinux.com/forum/threads/sid-meiers-pirates-guide.553/




public class DentalPatient {

  public int id = 0;
  public String name = "<empty>";
  public long age = 0;	public DentalPatient () { };
	/**
	 * Set the value of id
	 * @param newVar the new value of id
	 */
  public void setId (int newVar) {
  	id = newVar;
  }

	/**
	 * Get the value of id
	 * @return the value of id
	 */
  public int getId () {
  	return id;
  }

	/**
	 * Set the value of name
	 * @param newVar the new value of name
	 */
  public void setName (String newVar) {
  	name = newVar;
  }

	/**
	 * Get the value of name
	 * @return the value of name
	 */
  public String getName () {
  	return name;
  }

	/**
	 * Set the value of age
	 * @param newVar the new value of age
	 */
  public void setAge (long newVar) {
  	age = newVar;
  }

	/**
	 * Get the value of age
	 * @return the value of age
	 */
  public long getAge () {
  	return age;
  }

}













<?xml version="1.0" encoding="UTF-8"?>
<!-- Generated with glade 3.36.0 -->
<interface>
  <requires lib="gtk+" version="3.22"/>
  <object class="GtkWindow">
    <property name="can_focus">False</property>
    <child>
      <object class="GtkGrid">
        <property name="visible">True</property>
        <property name="can_focus">False</property>
        <child>
          <object class="GtkButton" id="add_btn">
            <property name="label" translatable="yes">button</property>
            <property name="visible">True</property>
            <property name="can_focus">True</property>
            <property name="receives_default">True</property>
          </object>
          <packing>
            <property name="left_attach">1</property>
            <property name="top_attach">0</property>
          </packing>
        </child>
        <child>
          <object class="GtkEntry">
            <property name="visible">True</property>
            <property name="can_focus">True</property>
          </object>
          <packing>
            <property name="left_attach">1</property>
            <property name="top_attach">1</property>
          </packing>
        </child>
        <child>
          <object class="GtkMenuButton" id="name">
            <property name="visible">True</property>
            <property name="can_focus">True</property>
            <property name="focus_on_click">False</property>
            <property name="receives_default">True</property>
            <child>
              <placeholder/>
            </child>
          </object>
          <packing>
            <property name="left_attach">0</property>
            <property name="top_attach">0</property>
          </packing>
        </child>
        <child>
          <placeholder/>
        </child>
        <child>
          <placeholder/>
        </child>
        <child>
          <placeholder/>
        </child>
        <child>
          <placeholder/>
        </child>
        <child>
          <placeholder/>
        </child>
        <child>
          <placeholder/>
        </child>
      </object>
    </child>
    <child type="titlebar">
      <placeholder/>
    </child>
  </object>
</interface>

