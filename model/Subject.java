package model;

interface Subject {
	public void registerObserver(IUsuarioObserver o);

	public void removeObserver(IUsuarioObserver o);

	public void notifyObservers();
}
