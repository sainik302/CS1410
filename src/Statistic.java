import java.util.*;

public class Statistic<SuperType> {

	private Map<Class<? extends SuperType>, Double> statMap;

	public Statistic() {
		statMap = new HashMap<Class<? extends SuperType>, Double>();

	}

	public final void update(Class<? extends SuperType> type, double amount) {
		if (statMap != null) {
			if (!statMap.containsKey(type)) {
				statMap.put(type, 0.0);
			}
			for (Class<? extends SuperType> currentType : statMap.keySet()) {
				if (currentType == type) {
					Double currentStat = statMap.get(type);

					currentStat += amount;

					statMap.replace(currentType, currentStat);

				}
			}

		}

	}

	public final double getValue(Class<?extends SuperType> key){
		if(key==null){
			return sum();
		}
		else if(statMap.containsKey(key)){
			return statMap.get(key);
		}else{
		return 0.0;
		}
	}


public double sum(){
	Double total=0.0;
	if(statMap !=null){
		for(Class<? extends SuperType> currentType : statMap.keySet()){
			total+=statMap.get(currentType);
		}
	}
	return total;

	}









}
