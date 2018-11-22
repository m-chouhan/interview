package problems;

import java.util.Scanner;

/**
 * https://codeforces.com/problemset/problem/488/C
 */
class Monster {

    static class Actor {
        public int hp, atk, def;

        boolean canWinAgainst(Actor enemy) {
            int lifeThis = (int) Math.ceil(hp / (enemy.atk - def));
            int lifeEnemy = (int) Math.ceil(enemy.hp / (atk - enemy.def));
            return lifeThis > lifeEnemy;
        }

        public Actor(int hp, int atk, int def) {
            this.hp = hp;
            this.atk = atk;
            this.def = def;
        }

        @Override
        public String toString() {
            return "hp : " + hp + ", atk : " + atk + ", def " + def;
        }
    }

    public static int getWinningHp(int atk, int def, Actor actor) {
        return (actor.hp * (actor.atk - def)) / (atk - actor.def) + 1;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Actor yoda = new Actor(sc.nextInt(), sc.nextInt(), sc.nextInt());
        Actor monster = new Actor(sc.nextInt(), sc.nextInt(), sc.nextInt());
        int costHp = sc.nextInt();
        int costAtk = sc.nextInt();
        int costDef = sc.nextInt();

        if (yoda.canWinAgainst(monster)) {
            System.out.println(0);
            return;
        }

        int minCost = Integer.MAX_VALUE;
        int maxAtk = monster.hp + monster.def;
        int maxDef = monster.atk;
        for (int atk = yoda.atk; atk <= maxAtk; atk++) {
            for (int def = yoda.def; def <= maxDef; def++) {
                int cost = (atk - yoda.atk) * costAtk +
                        (def - yoda.def) * costDef +
                        (getWinningHp(atk, def, monster) - yoda.hp) * costHp;
                minCost = Math.min(minCost, cost);
            }
        }

        System.out.println(minCost);
    }
}