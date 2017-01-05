package JavaTest;

/**
 * Created by chenyou729 on 16/12/20.
 */

public class Test {


    public static void main(String[] args) {
        System.out.println("hello:"+calculateRabit(5));
        printSuNumber(100,200) ;
        revertPoint() ;
    }

    /**
     * 1 1 2 3 5 8 13 21累加数据模型
     * @param x
     * @return
     */
    private static int calculateRabit(int x){
        if(x ==1 || x==2){
            return 1 ;
        }
        else {
            return calculateRabit(x-1)+calculateRabit(x-2) ;
        }
    }

    /**
     * 打印素数
     * @param from
     * @param end
     */
    private static void printSuNumber(int from ,int end){
        for(int i = from;i<=end;i++){
            int sqrt = (int) Math.sqrt(i);
            if(sqrt*sqrt==i){
                continue;
            }

            boolean isSu = true ;
            for(int j =2;j<sqrt;j++){
                if(i%j==0){
                    isSu = false ;
                    break;
                }
            }

            if(isSu){
                System.out.println(i);
            }
        }
    }



    public static Point revertList(Point point){
        if(point==null||point.nextPoint ==null){
            return point ;
        }

        Point temp = revertList(point.nextPoint);
        point.nextPoint.nextPoint = point ;
        point.nextPoint =null;
        return temp ;

    }

    private static  void revertPoint(){

        Point current = new Point() ;

        for(int i = 0 ;i<10;i++){
            current.number= i ;

            Point next = new Point() ;
            next.nextPoint = current;

            current = next ;
        }

        current = revertList(current) ;

        while (current!=null){
            System.out.println(current.number);
            current = current.nextPoint;
        }

    }
}
