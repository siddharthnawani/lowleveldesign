package goodreads.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pair<K,V>{
    K first;
    V second;
    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }
}
