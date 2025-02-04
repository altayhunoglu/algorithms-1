package algorithms.curated170.medium.killprocess;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class KillProcessRecursive {
    public static List<Integer> killProcessRecursive(List<Integer> pid, List<Integer> ppid, int kill)
    {
        HashMap<Integer, HashSet<Integer>> parentChildMap = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
            int par = ppid.get(i);
            if (par == 0) {
                if(pid.get(i) == kill)
                {
                    return pid;
                }
                continue;
            }
            int chi = pid.get(i);
            parentChildMap.putIfAbsent(par, new HashSet<>());
            parentChildMap.get(par).add(chi);
        }

        List<Integer> pTK = new LinkedList<>();
        recursiveHelper(pTK, parentChildMap, kill);
        return pTK;
    }
    private static void recursiveHelper(List<Integer> pTK, HashMap<Integer, HashSet<Integer>> parentChildMap, int kill)
    {
        pTK.add(kill);
        if (parentChildMap.containsKey(kill)) {
            for(int toKill : parentChildMap.get(kill))
            {
                recursiveHelper(pTK, parentChildMap, toKill);
            }
        }
    }

    public static void main(String[] args) {
        var pid = List.of(1, 3, 10, 5);
        var ppid = List.of(3, 0, 5, 3);
        var k3 = killProcessRecursive(pid, ppid, 3);
        System.out.println(k3);
    }
}
