package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class parcelaData {
	private final StringProperty bParc;
	private final StringProperty parIme;
	private final StringProperty parPov;
	private final StringProperty parTip;
	
	public parcelaData(String bParc,String parIme,String parPov,String parTip) {
		this.bParc=new SimpleStringProperty(bParc);
		this.parIme=new SimpleStringProperty(parIme);
		this.parPov=new SimpleStringProperty(parPov);
		this.parTip=new SimpleStringProperty(parTip);
		
	}

	public StringProperty bParcProperty() {
		return this.bParc;
	}
	

	public String getBParc() {
		return this.bParcProperty().get();
	}
	

	public void setBParc(final String bParc) {
		this.bParcProperty().set(bParc);
	}
	

	public StringProperty parTipProperty() {
		return this.parTip;
	}
	

	public String getParTip() {
		return this.parTipProperty().get();
	}
	

	public void setParTip(final String parTip) {
		this.parTipProperty().set(parTip);
	}
	

	public StringProperty parPovProperty() {
		return this.parPov;
	}
	

	public String getParPov() {
		return this.parPovProperty().get();
	}
	

	public void setParPov(final String parPov) {
		this.parPovProperty().set(parPov);
	}
	

	public StringProperty parImeProperty() {
		return this.parIme;
	}
	

	public String getParIme() {
		return this.parImeProperty().get();
	}
	

	public void setParIme(final String parIme) {
		this.parImeProperty().set(parIme);
	}
	
}
