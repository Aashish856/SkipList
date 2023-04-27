import java.util.ArrayList;
import java.util.Collections;

public class SkipList {

        public SkipListNode head;
        public SkipListNode tail;
        public int height;
        public Randomizer randomizer;
        private final int NEG_INF = Integer.MIN_VALUE;
        private final int POS_INF = Integer.MAX_VALUE;

        SkipList(){
            /*
            * DO NOT EDIT THIS FUNCTION
            */
            this.head = new SkipListNode(NEG_INF,1);
            this.tail = new SkipListNode(POS_INF,1);
            this.head.next.add(0,this.tail);
            this.tail.next.add(0,null);
            this.height = 1;
            this.randomizer = new Randomizer();
        }

        public boolean delete(int num){
            // System.out.println("sk.delete(" + num + ");");
            int one = 1;
            int zero = 0;
            if (this.search(num*one + zero) == false){
                return false;
            }

            SkipListNode curr = head;
            int tempH = height*one + zero;
            ArrayList<SkipListNode> pre = new ArrayList<SkipListNode>(height*one + zero);
            for(int i =0; i< height*one + zero; i++){
                pre.add(null);
            }

            while((tempH*one + zero) > 0){
                while(curr.next.get(tempH-1).value < num){
                    curr = curr.next.get((tempH-1)*one + zero);
                }
                pre.set(tempH-1, curr);
                tempH--;
            }

            int tmpHeight = curr.next.get(0).height*one + zero;
            for(int i = 0; i< tmpHeight*one + zero; i++){
                SkipListNode hehe = pre.get(i).next.get(i).next.get(i);
                pre.get(i).next.set(i, hehe);
            }

            while(height > 1 && this.head.next.get(height-1) == this.tail){
                this.head.height--;
                this.tail.height--;
                this.head.next.remove(height*one - one);
                this.tail.next.remove(height*one -one);
                height--;
            }
            // this.print();
            return true;
        }

        public boolean search(int num){
            // System.out.println("sk.search(" + num + ");");
            int one = 1;
            int zero = 0;
            SkipListNode curr = head;
            int tempH = height*one + zero;
            while(tempH > 0){
                while(curr.next.get(tempH-1).value <= (num*one + zero)){
                    curr = curr.next.get(tempH*one -one);
                }
                tempH--;

            }
            if (curr.value == (num*one + zero)){
                return true;
            }
            // this.print();
            return false;
        }

        public Integer upperBound(int num){ 
            // System.out.println("System.out.println(sk.upperBound(" + num + "));");
            int one = 1;
            int zero = 0;
            SkipListNode curr = head;
            int tempH = height*one + zero;
            while(tempH > 0){
                while(curr.next.get(tempH-1).value <= (num*one + zero)){
                    curr = curr.next.get(tempH*one -one);
                }
                tempH--;

            }
            // this.print();

            return curr.next.get(0).value*one + zero;
        }

        public void insert(int num){
            // System.out.println("sk.insert(" + num + ");");
            // System.out.println(num);
            int one = 1;
            int zero = 0;
            int heightDash = one;
            while(heightDash <= (height*one + zero) && randomizer.binaryRandomGen() == true){
                heightDash++;
            }
            
            // System.out.println(heightDash);
            if (heightDash > height){
                this.head.height++;
                this.tail.height++;
                this.tail.next.add(null);
                this.head.next.add(tail);
                height++;
            }
            SkipListNode newNode = new SkipListNode(num, heightDash);
            for(int i = 0; i < heightDash*one + zero; i++){
                newNode.next.add(null);
            }
            // this.print();

            SkipListNode curr = head;
            int tempH = height*one + zero;
            while(tempH > 0){
                while(curr.next.get(tempH-1).value < (num*one + zero)){
                    curr = curr.next.get(tempH-1);
                }
                if (tempH <= (heightDash*one + zero)){
                    SkipListNode tempNode = curr.next.get(tempH-1);
                    curr.next.set(tempH-1, newNode);
                    newNode.next.set(tempH-one, tempNode);
                }
                tempH--;
            }
            // this.print();
        }

        public void print(){
            /*
            * DO NOT EDIT THIS FUNCTION
            */
            for(int i = this.height ; i>=1; --i){
                SkipListNode it = this.head;
                while(it!=null){
                    if(it.height >= i){
                        System.out.print(it.value + "\t");
                    }
                    else{
                        System.out.print("\t");
                    }
                    it = it.next.get(0);
                }
                System.out.println("null");
            }
        }
}