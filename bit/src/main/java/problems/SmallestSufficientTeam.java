package problems;

import java.util.*;
import java.util.stream.Collectors;

public class SmallestSufficientTeam {

    public static int[][] cache = new int[1<<16][61];

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {

        if(people.size() == 0 || people.size() == 1) return new int [people.size()];

        int maxCacheSize = 1<<req_skills.length;
        int maxPeopleSize = people.size();
        for(int i = 0; i < maxCacheSize; ++i)
            for(int j = 0; j < maxPeopleSize; ++j)
                cache[i][j] = -1;

        HashMap<String, Integer> skillIds = fetchSkillIdsFrom(req_skills);
        ArrayList<Integer> pSkillCodeList = people.stream()
                                                .map(item -> getSkillCode(item, skillIds))
                                                .collect(Collectors.toCollection(ArrayList::new));

        int size = findOptimumDP((1<<req_skills.length) - 1, pSkillCodeList.size() - 1, pSkillCodeList);
        LinkedList<Integer> best = backTrackFrom((1<<req_skills.length) - 1, pSkillCodeList.size() - 1,
                                            pSkillCodeList, new LinkedList<Integer>());
        return best.stream().sorted().mapToInt(x -> x).toArray();
    }

    int getSkillCode(List<String> skills, HashMap<String, Integer> skillIds) {
        int skillCode = 0;
        for(String skill : skills)
            skillCode += 1<<skillIds.get(skill);
        return skillCode;
    }

    int findOptimumDP(int skillCode, int pId, ArrayList<Integer> pSkillList) {
        if(skillCode == 0) {
            return 0;
        }
        if(pId < 0) return Integer.MAX_VALUE;

        if(cache[skillCode][pId] != -1) return cache[skillCode][pId];

        int pSkillCode = pSkillList.get(pId);
        if(pId == 0) {
            cache[skillCode][pId] = ((skillCode & pSkillCode) == skillCode) ? 1 : Integer.MAX_VALUE;
            return cache[skillCode][pId];
        }
        //include
        int include = findOptimumDP(skillCode & ~pSkillCode, pId - 1, pSkillList);
        //exclude
        int exclude = findOptimumDP(skillCode, pId - 1, pSkillList);

        if(include == Integer.MAX_VALUE && exclude == Integer.MAX_VALUE)
            cache[skillCode][pId] = Integer.MAX_VALUE;
        else if(include == Integer.MAX_VALUE)
            cache[skillCode][pId] = exclude;
        else if(exclude == Integer.MAX_VALUE)
            cache[skillCode][pId] = include + 1;
        else cache[skillCode][pId] = Math.min(include + 1, exclude);

        return cache[skillCode][pId];
    }

    LinkedList<Integer> backTrackFrom(int skillCode, int pId, ArrayList<Integer> pSkillList, LinkedList<Integer> input) {
        if(skillCode == 0) return input;
        if(pId < 0) throw new RuntimeException("out of bounds: pid < 0");

        int pSkillCode = pSkillList.get(pId);
        if(pId == 0) {
            if((skillCode & pSkillCode) != skillCode)
                throw new RuntimeException("no solution found");
            input.add(pId);
            return input;
        }

        int include = cache[skillCode & ~pSkillCode][pId - 1];
        int exclude = cache[skillCode][pId -1];

        if(include == Integer.MAX_VALUE && exclude == Integer.MAX_VALUE)
            throw new RuntimeException("no solution found");
        else if(include == Integer.MAX_VALUE)
            return backTrackFrom(skillCode, pId - 1, pSkillList, input);
        else if(exclude == Integer.MAX_VALUE || include < exclude) {
            input.add(pId);
            return backTrackFrom(skillCode & ~pSkillCode, pId - 1, pSkillList, input);
        }
        return backTrackFrom(skillCode, pId - 1, pSkillList, input);
    }

    ////////////////////////////////////////////////////////////
    LinkedList<Integer> best = new LinkedList<>();

    public int[] smallestSufficientTeam2(String[] req_skills, List<List<String>> people) {

        if(people.size() == 0 || people.size() == 1) return new int [people.size()];

        HashMap<String, Integer> skillIds = fetchSkillIdsFrom(req_skills);
        List<Integer> peopleSkMap = removeRedundant(getPeopleSkMap(people, skillIds));
        List<List<Integer>> skToPeopleMap = getSkToPeopleMap(skillIds, peopleSkMap);

        findOptimum(skToPeopleMap, 0, new HashSet<Integer>());

        int []ans = new int[best.size()];
        int i = 0;
        for(int item : best) {
            ans[i++] = item;
        }
        return ans;
    }

    HashMap<String, Integer> fetchSkillIdsFrom(String []skills) {
        HashMap<String, Integer> skillId = new HashMap<>();
        int id = 0;
        for(String skill : skills)
            skillId.put(skill, id++);
        return skillId;
    }

    List<Integer> getPeopleSkMap(List<List<String>> people, HashMap<String, Integer> skId) {
        ArrayList<Integer> list = new ArrayList<>();
        for(List<String> skillList : people) {
            int skillMap = 0;
            for(String skill : skillList)
                skillMap |= 1 << skId.get(skill);
            list.add(skillMap);
        }
        return list;
    }

    List<Integer> removeRedundant(List<Integer> peopleSkillsList) {
        for(int i = 0; i < peopleSkillsList.size(); ++i)
            for(int j = i + 1; j < peopleSkillsList.size(); ++j) {
                //subsets are not required
                if( (peopleSkillsList.get(i) | peopleSkillsList.get(j)) ==
                        peopleSkillsList.get(j) ) {
                    peopleSkillsList.set(i, 0);
                }
            }
        return peopleSkillsList;
    }

    List<List<Integer>> getSkToPeopleMap(HashMap<String, Integer> skillIds,
                                         List<Integer> people) {
        List<List<Integer>> output = new ArrayList<>(skillIds.size());
        for(int i = 0; i < skillIds.size(); ++i)
            output.add(new ArrayList<>());

        for(int i = 0; i < people.size(); ++i) {
            int skills = people.get(i);
            for(int j = 0; j < output.size(); ++j)
                if( (skills & (1 << j)) != 0 )
                    output.get(j).add(i);
        }
        return output;
    }

    void findOptimum(List<List<Integer>> skToPeopleMap, int currentSkId,
                     HashSet<Integer> team) {
        if(currentSkId >= skToPeopleMap.size()) {
            if(team.size() < best.size() || best.size() == 0) {
                best.clear();
                best.addAll(team);
            }
            return;
        }

        for(int pId : skToPeopleMap.get(currentSkId)) {
            boolean shouldRemove = !team.contains(pId);
            team.add(pId);
            findOptimum(skToPeopleMap, currentSkId+1, team);
            if(shouldRemove) team.remove(pId);
        }
    }
}
