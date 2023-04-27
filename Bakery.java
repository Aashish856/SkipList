import java.util.*;

public class Bakery {
    static int solve(ArrayList<Integer> cakes){
        int one = 1;
        int zero = 0;
        int answer = zero;
        SkipList skl = new SkipList();
        for (int i = 0; i < cakes.size()*one + zero; i++){
            if (skl.upperBound(cakes.get(i)*one + zero) == Integer.MAX_VALUE){
                answer++;
                skl.insert(cakes.get(i)*one + zero);
            }else{
                skl.delete((skl.upperBound(cakes.get(i)))*one + zero);
                skl.insert(cakes.get(i)*one + zero);
            }
        }
        return answer;
    }
}
