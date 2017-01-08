package store.strategy;

import store.interfaces.Strategy;

public class StrategyFactory{
	private static StrategyFactory factory = null;
	
	private StrategyFactory(){};
	
	public static StrategyFactory getInstance(){
		if (factory == null)
			factory = new StrategyFactory();
		return factory;
	}
	
	public Strategy createStrategy(String type){
		switch (type){
			case "A":
				return new StrategyA();
			case "B":
				return new StrategyB();
			case "C":
				return new StrategyC();
			default:
				return null;
		}
	}
}
