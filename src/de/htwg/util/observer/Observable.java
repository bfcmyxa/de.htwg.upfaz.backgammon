package de.htwg.util.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Observable implements IObservable  {

	private List<IObserver> subscribers = new ArrayList<IObserver>(2);

	/* (non-Javadoc)
	 * @see de.htwg.util.observer.IObservable#addObserver(de.htwg.util.observer.IObserver)
	 */
	@Override
	public void addObserver(IObserver s) {
		subscribers.add(s);
	}

	/* (non-Javadoc)
	 * @see de.htwg.util.observer.IObservable#removeObserver(de.htwg.util.observer.IObserver)
	 */
	@Override
	public void removeObserver(IObserver s) {
		subscribers.remove(s);
	}

	/* (non-Javadoc)
	 * @see de.htwg.util.observer.IObservable#removeAllObservers()
	 */
	@Override
	public void removeAllObservers() {
		subscribers.removeAll(subscribers);
	}

	/* (non-Javadoc)
	 * @see de.htwg.util.observer.IObservable#notifyObservers()
	 */
	@Override
	public void notifyObservers() {
		for ( Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();) {
			IObserver observer = iter.next();
			observer.update();
		}
	}
}
