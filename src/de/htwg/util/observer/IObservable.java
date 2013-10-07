package de.htwg.util.observer;

public interface IObservable {

	public abstract void addObserver(IObserver s);

	public abstract void removeObserver(IObserver s);

	public abstract void removeAllObservers();

	public abstract void notifyObservers();

}