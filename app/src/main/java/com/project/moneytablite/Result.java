package com.project.moneytablite;

 class Result {
    private static String [] names= new String[100];
    private static int [] total = new int[100];
    private static int count =0;

     static int getCount() {
         return count;
     }

      public Result() {
         names[0]="nil";
         total[0]=0;
     }

     static StringBuilder getNames() {
         StringBuilder ab = new StringBuilder();

         for (int y = 0; y < count; y++) {

             ab.append(names[y]);
             ab.append('\n');

         }
         return ab;
     }

     static StringBuilder getTotal() {
         StringBuilder cb = new StringBuilder();

         for (int y = 0; y < count; y++) {
             cb.append(total[y]);
             cb.append('\n');

         }
         return cb;
     }

     static void addMember(String nme, int money){
         int flag = 0;

         for (int i = 0; i < count; i++) {
           if(names[i].equals(nme))
           {
               total[i]+= money;
               flag =1;
           }
       }
       if(flag ==0 && count<100) {
           names[count] = nme;
           total[count] = money;
           count++;
       }
   }
     static void check(){
       for(int i=0; i<count; i++){
           if(total[i]==0)
           {
               total[i]=total[count-1];
               names[i]=names[count-1];
               count--;
           }
       }
   }

     static String returnString()
   {
       StringBuilder sb = new StringBuilder();

       for(int y=0; y < count; y++){

           sb.append(names[y]);
           sb.append("\t \t"); // space
           sb.append(total[y]);
           sb.append('\n');

       }
       if(count==0)
       {
           return "No records Found";
       }

       return sb.toString();

   }
}
