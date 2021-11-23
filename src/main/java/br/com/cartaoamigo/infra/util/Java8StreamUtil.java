package br.com.cartaoamigo.infra.util;


import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Java8StreamUtil {

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
	    Map<Object,Boolean> seen = new ConcurrentHashMap<>();
	    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	
	public static boolean isValoresDuplicados(List<String> lista){
		List<String> valoresDuplicados = findDuplicateInStream(lista);
		return Objects.nonNull(valoresDuplicados) && !valoresDuplicados.isEmpty();
	}
	
	public static List<String> findDuplicateInStream(List<String> list){
        // Return the set of duplicate elements
        return list.stream()
  
                // Count the frequency of each element
                // and filter the elements
                // with frequency > 1
                .filter(i -> Collections.frequency(list, i) > 1)
  
                // And Collect them in a Set
                .collect(Collectors.toList());
    }

}
