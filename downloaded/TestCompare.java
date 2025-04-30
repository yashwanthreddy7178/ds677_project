class Card implements Comparable<Card>{ //加个泛型参数
    public String rank;
    public String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }
//比较两个对象是否相等
    @Override
    public boolean equals(Object obj) {
        //1
        if (this == obj) {
            return true;
        }
        //2
        if (obj == null) {
            return false;
        }
        //3.能否强转为Card
        if (!(obj instanceof Card)) {
            return false;
        }
        //4
        Card other = (Card)obj;

        return this.rank.equals(other.rank);
    }
//比较对象大小
    @Override
    public int compareTo(Card o) {
       int rank1 = this.convertRank();
       int rank2 = o.convertRank();
        return rank1-rank2;
    }
    private int convertRank() {
        if ("A".equals(rank)) {
            return 14;
        }
        if ("K".equals(rank)) {
            return 13;
        }
        if ("Q".equals(rank)) {
            return 12;
        }
        if ("J".equals(rank)) {
            return 11;
        }
        return Integer.parseInt(rank); //String转int
    }
}
public class TestCompare {
    public static void main(String[] args) {
        Card card1 = new Card("10","♥");
        Card card2 = new Card("10","♠");
        Card card3 = card1;
        System.out.println(card1.equals(card2));
        System.out.println(card3.equals(card2));
        System.out.println(card1.compareTo(card2));
    }

}
