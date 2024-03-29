import java.util.*;

class Solution {
    
    private String[] members = {"A", "C", "F", "J", "M", "N", "R", "T"};
    private int cnt = 0;
    private String[] conditions;
    
    public int solution(int n, String[] data) {
        conditions = data;
        
        String line = "";
        dd(line);
        return cnt;
    }
    private void dd(String line){
        if(line.length() == 8){
            if(check(line)){
                cnt++;
            }
            return;
        }
        for(int i = 0; i < 8; i++){
            if(!line.contains(members[i])){
                line += members[i];
                dd(line);
                line = line.substring(0, line.length() - 1);
            }
        }
    }
    
    private boolean check(String line){
        for(String expression : conditions){
            String v1 = expression.substring(0, 1);
            String v2 = expression.substring(2, 3);
            String func = expression.substring(3, 4);
            int amount = Integer.parseInt(expression.substring(4, 5)) + 1;
            
            int idx1 = line.indexOf(v1);
            int idx2 = line.indexOf(v2);
            int diff = Math.abs(idx1 - idx2);
            if(func.equals("=")){
                if(diff != amount){
                    return false;
                }
            }
            else if(func.equals("<")){
                if(diff >= amount){
                    return false;
                }
            }
            else{
                if(diff <= amount){
                    return false;
                }
            }
        }
        return true;
    }
}
