package favorite.任务拆分fockjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 多线程操作
 * fockJoin思想 任务拆分多个线程分别执行
 */
public class MyFockJoin {
    public static void main(String[] args) {

        ForkJoinPool p = new ForkJoinPool(3);
        Integer invoke = p.invoke(new Myplug(5));
        System.out.println("基本使用："+invoke);

        AharfAdd aharfAdd = new AharfAdd(1, 5);
        Integer invoke1 = p.invoke(aharfAdd);
        System.out.println("改进："+invoke1);
    }


}
class Myplug extends RecursiveTask<Integer> {

    //入参
    private Integer n;

    public Myplug(Integer n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if(n==1){
            return 1;
        }
        //拆分子任务加入线程
        Myplug myplug = new Myplug(n - 1);
        myplug.fork();

        //等待线程执行结果
        Integer join = myplug.join();
        return n+join;
    }
}

/**
 * 改进二分法拆分
 */
class AharfAdd extends  RecursiveTask<Integer>{
    private int begine;
    private int end;

    public int getBegine() {
        return begine;
    }

    public void setBegine(int begine) {
        this.begine = begine;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public AharfAdd(int begine, int end) {
        this.begine = begine;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if(begine==end){
            return begine;
        }
        if(begine <end &&( end - begine )==1){
            return begine+end;
        }
        int mid=(begine+end)/2;

        AharfAdd adda = new AharfAdd(begine, mid);
        AharfAdd addb = new AharfAdd(mid+1, end );
        adda.fork();
        addb.fork();

        return adda.join()+addb.join();
    }
}