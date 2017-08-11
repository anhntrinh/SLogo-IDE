package controller;

public interface BackEndHandler {
	public void setBackground(double index);
	public void setPalette(double index, double r, double g, double b);
	public void handleReset();
	public double getNumTurtle();
	public void setRelationship(Double id);
}
