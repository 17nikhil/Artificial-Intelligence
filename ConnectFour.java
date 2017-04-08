
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.concurrent.TimeUnit;
import java.util.*;

public class ConnectFour {
    private static final char[] players = new char[] { 'X', 'O' };

    private final int width, height;
    private final char[][] grid;
    private int lastCol = -1, lastTop = -1;
    public static int count;

    public ConnectFour(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new char[height][];
        for (int h = 0; h < height; h++) {
            Arrays.fill(this.grid[h] = new char[width], '.');
        }
    }

    public String toString() {
        return IntStream.range(0, this.width)
                        .mapToObj(Integer::toString)
                        .collect(Collectors.joining()) + "\n" +
               Arrays.stream(this.grid)
                     .map(String::new)
                     .collect(Collectors.joining("\n"));
    }

    public void chooseAndDrop(char symbol,int p,int pos) {
        do {
              int col;  
                if(p==1){
                     
                System.out.println("Computer's turn: hmmm.... ");
                 try{
                      Thread.sleep(1000);
                     }catch(InterruptedException ex){
                        Thread.currentThread().interrupt();
                     }
                   }
                 col = pos;
                
              
           if (! (0 <= col && col <this.width)) {
                System.out.println("Column must be between 0 and " +
                                   (this.width - 1));
                return;
            }
             for (int h = this.height - 1; h >= 0; h--) {
                if (this.grid[h][col] == '.') {
                    this.grid[this.lastTop=h][this.lastCol=col] = symbol;
                    return;
                }
            }

            System.out.println("Column " + col + " is full.");
            return;
        } while (true);
    }

    public boolean isWinningPlay() {
        if (this.lastCol == -1) {
            throw new IllegalStateException("No move has been made yet");
        }
        char sym = this.grid[this.lastTop][this.lastCol];
        //System.out.println(lastTop+ " " +lastCol);
        String streak = String.format("%c%c%c%c", sym, sym, sym, sym);
        return contains(this.horizontal(), streak) ||
               contains(this.vertical(), streak);
    }
   // checks if computer can win 
      public int computerWinsCheck() {
        if (this.lastCol == -1) {
            throw new IllegalStateException("No move has been made yet");
              }
        int x=isVertical();
        if(x!=-1)return x;
        else {
         x=isHorizontal();
           if(x!=-1) return x;
           else      return -1;
        }
     }
  // checks if the computer can win verically
    private int isVertical() {
         char sym = players[1];
         char add= '.';
        String streak = String.format("%c%c%c%c", add, sym, sym, sym);
        String sb1;
        for (int lastCol=0;lastCol<8;lastCol++)
         {
          StringBuilder sb = new StringBuilder(height);
          for (int h = 0; h < height; h++)
           {
            sb.append(this.grid[h][lastCol]);
           }
           sb1=sb.toString();
           if(contains(sb1,streak))
             return lastCol;
          }
         return -1;
     }
   // checks if the computer can win horizontally 
     private int isHorizontal(){
     char sym=players[1];
     char add='.';
      String streak1= String.format("%c%c%c%c",sym, sym, sym, add);
      String streak2 =String.format("%c%c%c%c",add,sym, sym, sym);
      String streak3=String.format("%c%c%c%c",sym,add, sym, sym);
      String streak4=String.format("%c%c%c%c",sym ,sym,add, sym);
      String sb1;
      int x=-1;
      for( int h=0;h<height;h++)
       {
          StringBuilder sb = new StringBuilder(width);
          for(int lastCol=0;lastCol<8;lastCol++)
          {
           sb.append(this.grid[h][lastCol]);
          }
          sb1=sb.toString();
                                  if(contains(sb1,streak1))
                                    {
                                     
                                      char[] str=sb1.toCharArray();
                                      for(int i=0;i<str.length;i++)
                                      {
                                        if(str[i]=='O' && str[i+1]=='O'&& str[i+2]=='O' && str[i+3]=='.')
                                         {
                                           x=i+3;
                                           return x;
                                         }
                                      }
                                     //return x;
                                    }
                              
                                   if(contains(sb1,streak2))
                                   {
                                      
                                      char[] str=sb1.toCharArray();
                                      for(int i=0;i<str.length;i++)
                                      {
                                        if(str[i]=='.' && str[i+1]=='O'&& str[i+2]=='O' && str[i+3]=='O')
                                         {
                                           x=i;
                                           return x;
                                         }
                                      }
                                     //return x;
                                    }
                                 if(contains(sb1,streak3))
                                   {
                                      
                                      char[] str=sb1.toCharArray();
                                      for(int i=0;i<str.length;i++)
                                      {
                                        if(str[i]=='O' && str[i+1]=='.'&& str[i+2]=='O' && str[i+3]=='O')
                                         {
                                           x=i+1;
                                           return x;
                                         }
                                      }
                                     //return x;
                                    }
                                if(contains(sb1,streak4))
                                   {
                                      
                                      char[] str=sb1.toCharArray();
                                      for(int i=0;i<str.length;i++)
                                      {
                                        if(str[i]=='O' && str[i+1]=='O'&& str[i+2]=='.' && str[i+3]=='O')
                                         {
                                           x=i+2;
                                           return x;
                                         }
                                      }
                                     //return x;
                                    }
                                 }
         return x;
       }

  // checks if the user can win
     public int winCheck() {
        if (this.lastCol == -1) {
            throw new IllegalStateException("No move has been made yet");
              }
        int x=itisvertical();
        if(x!=-1)return x;
        else {
         x=itishorizontal();
           if(x!=-1) return x;
           else      return -1;
        }
     }
 // Prunes the path where user can win-horizontally using ALPHA-BETA PRUNING.    
     private int itishorizontal(){
     char sym=players[0];
     char add='.';
      String streak1= String.format("%c%c%c%c",sym, sym, sym, add);
      String streak2 =String.format("%c%c%c%c",add,sym, sym, sym);
      String streak3=String.format("%c%c%c%c",sym,add, sym, sym);
      String streak4=String.format("%c%c%c%c",sym ,sym,add, sym);
      String sb1;
       int x=-1;
      for( int h=0;h<height;h++)
       {
          StringBuilder sb = new StringBuilder(width);
          for(int lastCol=0;lastCol<8;lastCol++)
          {
           sb.append(this.grid[h][lastCol]);
          }
          sb1=sb.toString();
                                  if(contains(sb1,streak1))
                                    {
                                     
                                      char[] str=sb1.toCharArray();
                                      for(int i=0;i<str.length;i++)
                                      {
                                        if(str[i]=='X' && str[i+1]=='X'&& str[i+2]=='X' && str[i+3]=='.')
                                         {
                                           x=i+3;
                                           return x;
                                         }
                                      }
                                    
                                    }
                              
                                   if(contains(sb1,streak2))
                                   {
                                      
                                      char[] str=sb1.toCharArray();
                                      for(int i=0;i<str.length;i++)
                                      {
                                        if(str[i]=='.' && str[i+1]=='X'&& str[i+2]=='X' && str[i+3]=='X')
                                         {
                                           x=i;
                                           return x;
                                         }
                                      }
                                    
                                    }
                                 if(contains(sb1,streak3))
                                   {
                                      
                                      char[] str=sb1.toCharArray();
                                      for(int i=0;i<str.length;i++)
                                      {
                                        if(str[i]=='X' && str[i+1]=='.'&& str[i+2]=='X' && str[i+3]=='X')
                                         {
                                           x=i+1;
                                           return x;
                                         }
                                      }
                                
                                    }
                                if(contains(sb1,streak4))
                                   {
                                      
                                      char[] str=sb1.toCharArray();
                                      for(int i=0;i<str.length;i++)
                                      {
                                        if(str[i]=='X' && str[i+1]=='X'&& str[i+2]=='.' && str[i+3]=='X')
                                         {
                                           x=i+2;
                                           return x;
                                         }
                                      }
                                     
                                    }
                                 }
         return x;
       }
       
 // prunes the path where user wins-vertically.     
       private int itisvertical() {
         char sym = players[0];
         char add= '.';
        String streak = String.format("%c%c%c%c", add, sym, sym, sym);
        String sb1;
        for (int lastCol=0;lastCol<8;lastCol++)
         {
          StringBuilder sb = new StringBuilder(height);
          for (int h = 0; h < height; h++)
           {
            sb.append(this.grid[h][lastCol]);
           }
           sb1=sb.toString();
           if(contains(sb1,streak))
             return lastCol;
          }
         return -1;
        
       
    }
    private String horizontal() {
        return new String(this.grid[this.lastTop]);
    }
    
     private String vertical() {
        StringBuilder sb = new StringBuilder(this.height);
        for (int h = 0; h < this.height; h++) {
            sb.append(this.grid[h][this.lastCol]);
        }
        return sb.toString();
    }


    private static boolean contains(String haystack, String needle) {
        return haystack.indexOf(needle) >= 0;
    }

    public static void main(String[] args) {
    int pos;
    Random rand = new Random();
        try (Scanner input = new Scanner(System.in)) {
            int height = 6, width = 8, moves = height * width;
            ConnectFour board = new ConnectFour(width, height);
            System.out.println("Only one chance per player ");
            System.out.println("welcome to ConnectFour- Our team's project!");
            System.out.println("Can you beat our program? Try your luck");
	    System.out.println();
             try{
                      Thread.sleep(2000);
                     }catch(InterruptedException ex){
                        Thread.currentThread().interrupt();
                     }
                   

            System.out.println("Use 0-" + (width - 1) + " to choose a column.");
            System.out.println(board);

            for (int player = 0; moves-- > 0; player = 1 - player) {
                char symbol = players[player];
               if(board.lastCol!=-1 && player==1)
                               {
                                    int y=board.computerWinsCheck();
                                    int x=board.winCheck();
                                    if(y!=-1)
                                    {
                                    board.chooseAndDrop(symbol,player,y);
                                   
                                    }
                                    
                                    else if(x!=-1)
                                      {
                                    board.chooseAndDrop(symbol,player,x);
                                    
                                      }
                                    else{
                                     pos = rand.nextInt(7-0)+0;
                                     board.chooseAndDrop(symbol,player,pos);
                                    
                                     }
                              }
                else
                 {
                     System.out.print("\nPlayer " + symbol + " turn: ");
                     pos=input.nextInt();
                     board.chooseAndDrop(symbol,player,pos);
                   
                 }
                 
                 System.out.println(board);
                 System.out.println("-----------------------------------------------------------");
             
                                              
                if (board.isWinningPlay()) {
                   if(symbol=='O')
                     System.out.println("Computer wins! Told you that you can't beat me");
                   else
                      System.out.println("Player " + symbol + " wins! You just got lucky this time");
                    return;
                }
            
            }
            System.out.println("Game over, no winner. Better luck next time");
        }
    }
}
