
 class RobotControl
 {
   private Robot r;
   
   public RobotControl(Robot r)
   {
       this.r = r;
   }

   public void control(int barHeights[], int blockHeights[], int required[], boolean ordered)
   {
                  controlPartA(barHeights,blockHeights);        //Part A
                 controlPartB(barHeights,blockHeights);        //Part B
                   controlPartC(barHeights,blockHeights);        //Part C
                   controlPartD(barHeights,blockHeights,required,ordered); //Part D
   }
   
 
public void controlPartA(int barHeights[], int blockHeights[])
   { 
	   int constraint=14;              // height of the arm should not exceed 14
	   int SourceHeight=8;             // total height of the blocks in the source column
	   int SourceColumnPosition=10;    // position of source column
	   int TargetColumn=1;             //target column position
	   int TargetColumnSize=0;         // initial size of the target column
	   int height=2;                   // height value
	   int width=1;                    // width value
	   int depth=0;                    // depth value
	  
	 for(int i=0;i<=3;i++)
	 {
	       while(height<constraint)         // height of the arm increases for every iteration until it's height value doesn't exceed the constraint value 
	        {
		       r.up();                      // up method is utilized to increase the height of the arm
		      height++;                     // height increases after every iteration
	        }
		   
	      while(width<SourceColumnPosition)  //if the value of width is less than the position of the source column,the arm extends to the right
	        {
		      r.extend();                    // extend method is used for moving to the source column.
		      width++;                       // width increases after every iteration
	        }
		   
	      while(height - 1 - depth > SourceHeight)    //even when height of the arm is reduced by the depth value and the value 1 for lowering,it should be greater than the Source Height
	        {
		      r.lower();                           //lower method is made use of in lowering the arm
		      depth++;                             //depth value increases as the arm lowers
	        }
	          
	          r.pick();                            // pick method is used to pick the blocks from the Source Column
	          SourceHeight-=2;                     // Since all the blocks are of Size 2,when a block is picked,Source height decreases by value 2
		  
	      while(depth>0)                           //if the depth value is greater than 0,the block is raised all the way to the top
	        {
		      r.raise();                           // raise method is used to raise the arm
		      depth--;                             // depth value decreases as the block is raised 
	        }
	       
	      while(width>TargetColumn)                //Now the width value is greater than the target column , the arm is contracted to column 1 to place the blocks
	        {
		      r.contract();                        // contract method helps to contract the arm
		      width--;                             // width value decreases as the arm contracts to reach the target column
	        }
	      
	      while(height-1-2-depth>TargetColumnSize)  //height value decreases by the value 1 for lowering and the block size of value 2 and depth value and it should be greater than target column size
	       {
		      r.lower();
		      depth++;                              //depth value increases as height decreases
	       }                                                           
	      r.drop();                                 // drop method is used to drop the block
	     
	      while(depth>0)                            // Arm should raise for picking the next block,while the depth is greater,height increases as depth gradually decrease
	       {
		     r.raise();
		     depth--;
	       }
	      TargetColumnSize+=2;    //Target column size is increased by value 2 for every block placed 
    }
	    // looping efficiently repeats the process for the next three blocks rather than doing it over again
}
	   
public void controlPartB(int barHeights[], int blockHeights[])
   { 
	   
	   int constraint=14;                        // height of the arm should not exceed 14
	   int SourceHeight=8;                       // total height of the blocks in the source column
	   int SourceColumnPosition=10;              // Source Column position
	   int TargetColumn=1;                       // target column position
	   int TargetColumnSize=0;                   // initial size of the target column
	   int h=2;                                  // height value
	   int w=1;                                  // width value
	   int d=0;                                  // depth value
	 
for(int i=0;i<=3;i++)
	 {
		 int max=barHeights[0];                  //initializing the max variable with barHeights array element
		 
		 for(int j=0;j<barHeights.length;j++)   
		   {
			   
			     if(barHeights[j]>max)           //initialized first array element is compared with every other barHeights array element
			    	                             //it gets replaced with max elements found after every iteration
			    	                             // And it is assigned with the max element in the array at the end .
			    	 {
				        max=barHeights[j];
		             }            
			}
		 
		  int largest=Math.max(max,SourceHeight);   //finds the largest of the bar heights and source height value
		
		  // To safely move the blocks to the target column without hitting the bars 
		  
		  while(largest<constraint)                 // largest value is compared with constraint value and constraint value decreases if it's much higher than largest value
		 
		    {
			 largest++;                           // largest value increases after every iteration
			 constraint--;                        // constraint value decreases for every iteration
			}
		  
		  while(h<constraint)                      //height increases as per the new value of the constraint
	       
		   {
		    r.up();                                 // up method is utilized to increase the height of the arm
		    h++;                                    // height value increases for every iteration
	       }
	      
	     while(w<SourceColumnPosition)            //if the value of width is less than the position of the source column,the arm extends to the right
	      {
		    r.extend();                           //extend method is utilized for moving to the source column.
		    w++;                                  //width increases for every iteration
	      }
		  
	     while(h - 1 - d > SourceHeight)          //even when height of the arm is reduced by the depth value and the value 1 for lowering,it should be greater than the Source Height
	      
	     {
		   r.lower();                            //lower method is made use of in lowering the arm
		   d++;                                  //depth value increases for every iteration
	      }
	   
	    r.pick();                // pick method is used to pick the blocks from the Source Column
	    SourceHeight-=2;         // Since all the blocks are of Size 2,when a block is picked,Source height decreases by value 2
		   
	     while(d>0)               //if the depth value is greater than 0,the block is raised all the way to the top
	     
	      {
		   r.raise();           // raise method is used to raise the arm
		   d--;                 //depth value decreases for every iteration
	      }
	     
	     while(w>TargetColumn)     //Now the width value is greater than the target column , the arm is contracted to column 1 to place the blocks
	   
	       {
		   r.contract();         // contract method helps to contract the arm
		   w--;                  //width value decreases for every iteration
	       }
	   
	     while(h-1-2-d>TargetColumnSize)   //height value decreases by the value 1 for lowering and the block size of value 2 and depth value and it should be greater than target column size
	       
	       {
		   r.lower();          
		   d++;                     //depth value increases for every iteration
	       }
	 
	     r.drop();            // drop method is used to drop the block
	 
	     while(d>0)           // Arm should raise for picking the next block,while the depth is greater,height increases as depth gradually decrease
	   {
		   r.raise();
		   d--;              //depth value decreases for every iteration
	   }
	     TargetColumnSize+=2;      //Target column size is increased by value 2 for every block placed 
	
	 }    // looping efficiently repeats the process for the next three blocks rather than doing it over again
}
 

 public void controlPartC(int barHeights[], int blockHeights[])
 { 
	   
	   int constraint=14;                   // height of the arm should not exceed 14
	   int SourceColumnPosition=10;         // source column position
	   int TargetColumn=1;                  // target column position
	   int TargetColumnSize=0;              // size of the target column 
	   int h=2;                             // height value
	   int w=1;                             // width value
	   int d=0;                             // depth value
	 
	   int SourceHeight=0;      //Since Blocks are provided as input by user,Source Height needs to be calculated
	   
	   int max=barHeights[0];   ////initializing the max variable with barHeights array element
		 
	   for(int j=0;j<barHeights.length;j++) 
		 {
			 if(barHeights[j]>max)       //initialized first array element is compared with every other barHeights array element
			   {                         //it gets replaced with max elements found after every iteration
                                         // And it is assigned with the max element in the array at the end .
			      max=barHeights[j];
	           
			   }   
		 } 
		
		 for(int k=0;k<blockHeights.length;k++)
		 {
		     SourceHeight=SourceHeight+blockHeights[k]; //Sourceheight is calculated by adding the elements in block heights array
		 }
	  
		 int FirstBlockHeight = blockHeights[0];    // first array element 
		 
		 int SecondBlockHeight = blockHeights[1];   // second array element 
		 
		 int ThirdBlockHeight = blockHeights[2];    // third array element
		 
		 int FourthBlockHeight = blockHeights[3];   // fourth array element
		 
//Picking the Topmost block - Fourth Block  		
	
	// for moving the blocks without hitting the bars	 
    int  Source=max-(SourceHeight	- FourthBlockHeight);  
	
    if(Source<constraint)            // Source value should be less than constraint
	{
		constraint=constraint-1;        
	}
		
	while(h<constraint)
	   {
		   
		   r.up();           // up method is utilized to increase the height of the arm
		   h++;       //height value increases for every iteration
	   }
	      
	while(w<SourceColumnPosition)
	   {
		   r.extend();      //extend method is utilized for moving to the source column.
		   w++;            //width value increases for every iteration
	   }
	    
	while(h  - 1 -  d > SourceHeight )
	   {
		   r.lower();      //lower method is made use of in lowering the arm
		   d++;            // depth value increases for every iteration
	   }
	   
	r.pick();       // pick method is used to pick the blocks from the Source Column
	    
	while(d>0)
	   {
		   r.raise();    // raise method is used to raise the arm
		   d--;          //depth value decreases for every iteration
	   }
	   
    while(w>TargetColumn)
	   
       {
		   r.contract();   // contract method helps to contract the arm
		   w--;           //width value decreases for every iteration
	   }
	   
	while(h-1-FourthBlockHeight-d>TargetColumnSize)
	   {
		   r.lower();     //lower method is made use of in lowering the arm
		   d++;           //depth value increases for every iteration
	   }
	 
	r.drop();    // drop method is used to drop the block
	 
	while(d>0)
	   {
		   r.raise();   // raise method is used to raise the arm
		   d--;         //depth value decreases for every iteration
	   }
	   
	TargetColumnSize+=FourthBlockHeight;
	 
// Picking the Next Block - Third Block
	
	// for moving the blocks without hitting the bars	
	int SourceHeight2=SourceHeight-FourthBlockHeight;
	int  Source2=max-(SourceHeight2	- ThirdBlockHeight);
		
	if(Source2<constraint)
		{
			constraint=constraint-1;
		}
	
	while(h<constraint)
		   
	    {
			 r.up();           // up method is utilized to increase the height of the arm
			 h++;          //height value increases for every iteration
		}
		      
	while(w<SourceColumnPosition)
		   
		{
			   r.extend();    //extend method is utilized for moving to the source column.
			   w++;             //width increases for every iteration
		}
		    
     while(h  - 1 -  d > SourceHeight2 )
		   
        {
			   r.lower();           //lower method is made use of in lowering the arm
			   d++;
		}
		   
     r.pick();       // pick method is used to pick the blocks from the Source Column
		   
	 while(d>0)
		   
	     {
			   r.raise();            // raise method is used to raise the arm
			   d--;                 //depth value decreases for every iteration
		 }
	 
	 while(w>TargetColumn)
		   
	      {
			   r.contract();         // contract method helps to contract the arm
			   w--;
		   }
		   
	 while(h-1-ThirdBlockHeight-d>TargetColumnSize)
		   
	       {
			   r.lower();                //lower method is made use of in lowering the arm
			   d++;
		   }
		 
	r.drop();       // drop method is used to drop the block
		 
    while(d>0)
		   
        {
			 r.raise();        // raise method is used to raise the arm
			 d--;               //depth value decreases for every iteration
		}
		   
    TargetColumnSize+=ThirdBlockHeight;
		 
 // Picking the Second Block 
    
 // for moving the blocks without hitting the bars	
	int   SourceHeight3=SourceHeight-FourthBlockHeight-ThirdBlockHeight;
		   
	int  Source3=max-(SourceHeight3	- SecondBlockHeight);
			
	if(Source3<constraint)
			{
				constraint=constraint-1;
			}
				
	while(h<constraint)
		
	        {
				   
			     r.up();         // up method is utilized to increase the height of the arm
				 h++;            //height value increases for every iteration
			}
			      
    while(w<SourceColumnPosition)
			   
            {
				   r.extend();          //extend method is utilized for moving to the source column.
				   w++;            //width increases for every iteration
			}
			    
	while(h  - 1 -  d > SourceHeight3 )
			   
	        {
				   r.lower();            //lower method is made use of in lowering the arm
				   d++;
			 }
			   
	r.pick();         // pick method is used to pick the blocks from the Source Column
			   
	while(d>0)
	
	         {
				   r.raise();                 // raise method is used to raise the arm
				   d--;                       //depth value decreases for every iteration
			 }
			   
	while(w>TargetColumn)
			   {
				   r.contract();         // contract method helps to contract the arm
				   w--;
			   }
			   
    while(h-1-SecondBlockHeight-d>TargetColumnSize)
			   
               {
				   r.lower();                 //lower method is made use of in lowering the arm
				   d++;
			   }
			 
    r.drop();    // drop method is used to drop the block
			 
	while(d>0)
	       
	       {
				   r.raise();              // raise method is used to raise the arm
				   d--;                    //depth value decreases for every iteration
		   }
			   
	TargetColumnSize+=SecondBlockHeight;
			   
//Picking the Bottom Block - First Block    
	
	// for moving the blocks without hitting the bars	
	int   SourceHeight4=SourceHeight-FourthBlockHeight-ThirdBlockHeight-SecondBlockHeight;
			   
    int  Source4=max-(SourceHeight4	- FirstBlockHeight);
				
    if(Source4<constraint)
				{
					constraint=constraint-1;
				}
					
	while(h<constraint)
				   
	               {
				       r.up();                // up method is utilized to increase the height of the arm
					   h++;                    //height value increases for every iteration
				   }
				      
	while(w<SourceColumnPosition)
				   
	               {
					   r.extend();          //extend method is utilized for moving to the source column.
					   w++;                //width increases for every iteration
				   }
				    
    while(h  - 1 -  d > SourceHeight4 )
				   
                   {
					   r.lower();           //lower method is made use of in lowering the arm
					   d++;
				   }

    r.pick();             // pick method is used to pick the blocks from the Source Column
				   
	while(d>0)
		
	      {
				r.raise();         // raise method is used to raise the arm
			    d--;                 //depth value decreases for every iteration
		  }
				   
	while(w>TargetColumn)
				   
	              {
					   r.contract();          // contract method helps to contract the arm
					   w--;
				   }
				   
    while(h-1-FirstBlockHeight-d>TargetColumnSize)
				   
                  {
					   r.lower();            //lower method is made use of in lowering the arm
					   d++;
				   }
				 
	r.drop();      // drop method is used to drop the block
				 
	while(d>0)
				
	              {
					   r.raise();                // raise method is used to raise the arm
					   d--;                      //depth value decreases for every iteration
				   }
				   
	TargetColumnSize+=FirstBlockHeight;
			 
		 
	 }
 
 
 public void controlPartD(int barHeights[], int blockHeights[],int required[], boolean ordered)
 { 
	   
	   int constraint=14;                   // height of the arm should not exceed 14
	   int SourceColumnPosition=10;
	   int TargetColumn=1;
	   int TargetColumnSize=0;
	   int TempColumn=9;
	   int h=2;
	   int w=1;
	   int d=0;
	   int TempColumnSize=0;
	   int temp[]=new int[4];    //temp array
	 
	   int SourceHeight=0;      //Since Blocks are provided as input by user,Source Height needs to be calculated
	   
	   int max=barHeights[0];   ////initializing the max variable with barHeights array element
		 
	   for(int j=0;j<barHeights.length;j++) 
		 {
			 if(barHeights[j]>max)       //initialized first array element is compared with every other barHeights array element
			   {                         //it gets replaced with max elements found after every iteration
                                         // And it is assigned with the max element in the array at the end .
			      max=barHeights[j];
	           
			   }   
		 } 
		
		 for(int k=0;k<blockHeights.length;k++)
		 {
		     SourceHeight=SourceHeight+blockHeights[k]; //Sourceheight is calculated by adding the elements in block heights array
		 }
	  
		 int FirstBlockHeight = blockHeights[0];    // first array element 
		 
		 int SecondBlockHeight = blockHeights[1];   // second array element 
		 
		 int ThirdBlockHeight = blockHeights[2];    // third array element
		 
		 int FourthBlockHeight = blockHeights[3];   // fourth array element
		 
		 for(int i=0;i<required.length;i++)
		 {
			 System.out.println(required[i]);
		 }
		 System.out.println(required[0]);
		 System.out.println(required[1]);
		 System.out.println(required[2]);
		 System.out.println(required[3]);
		 
//Picking the Topmost block - Fourth Block  
		 
	
    int  Source=max-(SourceHeight	- FourthBlockHeight);
	
    if(Source<constraint)
	{
		constraint=constraint-1;        
	}
		
	while(h<constraint)
	   {
		   
		   r.up();           // up method is utilized to increase the height of the arm
		   h++;              //height value increases for every iteration
	   }
	      
	while(w<SourceColumnPosition)
	   {
		   r.extend();       //extend method is utilized for moving to the source column.
		   w++;                //width increases for every iteration
	   }
	    
	while(h  - 1 -  d > SourceHeight )
	   {
		   r.lower();               //lower method is made use of in lowering the arm
		   d++;
	   }
	   
	r.pick();           // pick method is used to pick the blocks from the Source Column
	    
	while(d>0)
	   {
		   r.raise();
		   d--;                  //depth value decreases for every iteration
	   }
	//If my required first block is the same as the fourth block in the source column.
	if(required[0]==blockHeights[3])
	{
	   
    while(w>TargetColumn)
	   
       {
		   r.contract();       // contract method helps to contract the arm
		   w--;
	   }
	   
	while(h-1-FourthBlockHeight-d>TargetColumnSize)
	   {
		   r.lower();            //lower method is made use of in lowering the arm
		   d++;
		   
	   }
	 
	r.drop();  // drop method is used to drop the block
	 
	while(d>0)
	   {
		   r.raise();               // raise method is used to raise the arm
		   d--;                    //depth value decreases for every iteration
	   }
	   
	TargetColumnSize+=FourthBlockHeight;}
	//If my required second block is the same as the fourth block in the source column.
	else if(required[1]==blockHeights[3])
	{
	   
    while(w>TargetColumn)
	   
       {
		   r.contract();             // contract method helps to contract the arm
		   w--;
	   }
	   
	while(h-1-FourthBlockHeight-d>TargetColumnSize)
	   {
		   r.lower();              //lower method is made use of in lowering the arm
		   d++;
		   
	   }
	 
	r.drop(); // drop method is used to drop the block
	 
	while(d>0)
	   {
		   r.raise();
		   d--;                 //depth value decreases for every iteration
	   }
	   
	TargetColumnSize+=FourthBlockHeight;}
	//If my required third block is the same as the fourth block in the source column.
	else if(required[2]==blockHeights[3])
	{
	   
    while(w>TargetColumn)
	   
       {
		   r.contract();          // contract method helps to contract the arm
		   w--;
	   }
	   
	while(h-1-FourthBlockHeight-d>TargetColumnSize)
	   {
		   r.lower();                  //lower method is made use of in lowering the arm
		   d++;
		   
	   }
	 
	r.drop(); // drop method is used to drop the block
	 
	while(d>0)
	   {
		   r.raise();          // raise method is used to raise the arm
		   d--;                   //depth value decreases for every iteration
	   }
	   
	TargetColumnSize+=FourthBlockHeight;}
	//If my required fourth block is the same as the fourth block in the source column.
	else if(required[3]==blockHeights[3])
	{
	   
    while(w>TargetColumn)
	   
       {
		   r.contract();        // contract method helps to contract the arm
		   w--;
	   }
	   
	while(h-1-FourthBlockHeight-d>TargetColumnSize)
	   {
		   r.lower();                 //lower method is made use of in lowering the arm
		   d++;
		   
	   }
	 
	r.drop(); // drop method is used to drop the block
	 
	while(d>0)
	   { 
		   r.raise();         // raise method is used to raise the arm
		   d--;            //depth value decreases for every iteration
	   }
	   
	TargetColumnSize+=FourthBlockHeight;}
	//moving the block to temp column
	else {
		while(w>TempColumn)
			   
	       {
			   r.contract();       // contract method helps to contract the arm
			   w--;
		   }
		   
		while(h-1-blockHeights[3]-d>TempColumnSize)
		   {
			   r.lower();                  //lower method is made use of in lowering the arm
			   d++;
			   
		   }
		 
		r.drop();  // drop method is used to drop the block
		 
		while(d>0)
		   {
			   r.raise();
			   d--;                      //depth value decreases for every iteration
		   }
		   
		TempColumnSize+=blockHeights[3];
		temp[0]=blockHeights[3];}
	
	
	 
// Picking the Next Block - Third Block
	
	int SourceHeight2=SourceHeight-FourthBlockHeight;
	int  Source2=max-(SourceHeight2	- ThirdBlockHeight);
		
	if(Source2<constraint)
		{
			constraint=constraint-1;
		}
	
	while(h<constraint)
		   
	    {
			 r.up();             // up method is utilized to increase the height of the arm
			 h++;               //height value increases for every iteration
		}
		      
	while(w<SourceColumnPosition)
		   
		{
			   r.extend();           //extend method is utilized for moving to the source column.
			   w++;                //width increases for every iteration
		}
		    
     while(h  - 1 -  d > SourceHeight2 )
		   
        {
			   r.lower();                //lower method is made use of in lowering the arm
			   d++;
		}
		   
     r.pick();      // pick method is used to pick the blocks from the Source Column
		   
	 while(d>0)
		   
	     {
			   r.raise();          // raise method is used to raise the arm
			   d--;                   //depth value decreases for every iteration
		 }
	//If my required second block is the same as the third block in the source column.
	 if(required[1]==blockHeights[2])
		{
	 
	 while(w>TargetColumn)
		   
	      {
			   r.contract();          // contract method helps to contract the arm
			   w--;
		   }
		   
	 while(h-1-ThirdBlockHeight-d>TargetColumnSize)
		   
	       {
			   r.lower();                //lower method is made use of in lowering the arm
			   d++;
		   }
		 
	r.drop(); // drop method is used to drop the block
		 
    while(d>0)
		   
        {
			 r.raise();               // raise method is used to raise the arm
			 d--;                      //depth value decreases for every iteration
		}
		   
    TargetColumnSize+=ThirdBlockHeight;}
	//If my required third block is the same as the third block in the source column.
	 else if(required[2]==blockHeights[2])
		{
	 
	 while(w>TargetColumn)
		   
	      {
			   r.contract();           // contract method helps to contract the arm
			   w--;
		   }
		   
	 while(h-1-ThirdBlockHeight-d>TargetColumnSize)
		   
	       {
			   r.lower();             //lower method is made use of in lowering the arm
			   d++;
		   }
		 
	r.drop(); // drop method is used to drop the block
		 
while(d>0)
		   
  {
			 r.raise();                  // raise method is used to raise the arm
			 d--;                         //depth value decreases for every iteration
		}
		   
TargetColumnSize+=ThirdBlockHeight;}
	//If my required fourth block is the same as the third block in the source column.
	 else if(required[3]==blockHeights[2])
		{
	 
	 while(w>TargetColumn)
		   
	      {
			   r.contract();        // contract method helps to contract the arm
			   w--;
		   }
		   
	 while(h-1-ThirdBlockHeight-d>TargetColumnSize)
		   
	       {
			   r.lower();              //lower method is made use of in lowering the arm
			   d++;
		   }
		 
	r.drop(); // drop method is used to drop the block
		 
 while(d>0)
		   
     {
			 r.raise();
			 d--;                      //depth value decreases for every iteration
		}
		   
 TargetColumnSize+=ThirdBlockHeight;}
	//If my required first block is the same as the third block in the source column.
	 else if(required[0]==blockHeights[2])
		{
	 
	 while(w>TargetColumn)
		   
	      {
			   r.contract();            // contract method helps to contract the arm
			   w--;
		   }
		   
	 while(h-1-ThirdBlockHeight-d>TargetColumnSize)
		   
	       {
			   r.lower();             //lower method is made use of in lowering the arm
			   d++;
		   }
		 
	r.drop(); // drop method is used to drop the block
		 
while(d>0)
		   
  {
			 r.raise();             // raise method is used to raise the arm
			 d--;                    //depth value decreases for every iteration
		}
		   
TargetColumnSize+=ThirdBlockHeight;}
	 //moving the block to temp column
	 else {
			while(w>TempColumn)
				   
		       {
				   r.contract();         // contract method helps to contract the arm
				   w--;
			   }
			   
			while(h-1-blockHeights[2]-d>TempColumnSize)
			   {
				   r.lower();               //lower method is made use of in lowering the arm
				   d++;
				   
			   }
			 
			r.drop(); // drop method is used to drop the block
			 
			while(d>0)
			   {
				   r.raise();            // raise method is used to raise the arm
				   d--;                  //depth value decreases for every iteration
			   }
			   
			TempColumnSize+=blockHeights[2];}
	//If my required second block is the same as the first block in the temp column.
	 if(required[1]==temp[0])
		{
			while(h<constraint)
				   
	        {
			       r.up();     // up method is utilized to increase the height of the arm
				   h++;       //height value increases for every iteration
			   }
			      
	while(w<TempColumn)
			   
	        {
				   r.extend();      
				   w++;               //width increases for every iteration
			   }
			    
	while(h  - 1 -  d > TempColumnSize )
			   
	        {
				   r.lower();           //lower method is made use of in lowering the arm
				   d++;
			   }

	r.pick();    // pick method is used to pick the blocks from the temp Column
			   
	while(d>0)

	{
			r.raise();            // raise method is used to raise the arm
		    d--;                   //depth value decreases for every iteration
	}
	while(w>TargetColumn)
		   
	{
		   r.contract();          // contract method helps to contract the arm
		   w--;
	}

	while(h-1-temp[0]-d>TargetColumnSize)
	{
		   r.lower();            //lower method is made use of in lowering the arm
		   d++;
		   
	}

	r.drop(); // drop method is used to drop the block

	while(d>0)
	{
		   r.raise();          // raise method is used to raise the arm
		   d--;                 //depth value decreases for every iteration
	}

	TargetColumnSize+=temp[0];
		 }
	//If my required second block is the same as the second block in the temp column.
	 else if(required[1]==temp[1])
		{
			while(h<constraint)
				   
	        {
			       r.up();         // up method is utilized to increase the height of the arm
				   h++;            //height value increases for every iteration
			   }
			      
	while(w<TempColumn)
			   
	        {
				   r.extend();            //extend method is utilized for moving to the temp column.
				   w++;                //width increases for every iteration
			   }
			    
	while(h  - 1 -  d > TempColumnSize )
			   
	        {
				   r.lower();            //lower method is made use of in lowering the arm
				   d++;
			   }

	r.pick();  // pick method is used to pick the blocks from the temp Column
			   
	while(d>0)

	{
			r.raise();          // raise method is used to raise the arm
		    d--;                //depth value decreases for every iteration
	}
	while(w>TargetColumn)
		   
	{
		   r.contract();           // contract method helps to contract the arm
		   w--;
	}

	while(h-1-temp[1]-d>TargetColumnSize)
	{
		   r.lower();            //lower method is made use of in lowering the arm
		   d++;
		   
	}

	r.drop(); // drop method is used to drop the block

	while(d>0)
	{
		   r.raise();            // raise method is used to raise the arm
		   d--;                 //depth value decreases for every iteration
	}

	TargetColumnSize+=temp[1];
		 }
	//If my required second block is the same as the third block in the temp column.
	 else if(required[1]==temp[2])
		{
			while(h<constraint)
				   
	        {
			       r.up();       // up method is utilized to increase the height of the arm
				   h++;           //height value increases for every iteration
			   }
			      
	while(w<TempColumn)
			   
	        {
				   r.extend();         //extend method is utilized for moving to the temp column.
				   w++;                //width increases for every iteration
			   }
			    
	while(h  - 1 -  d > TempColumnSize )
			   
	        {
				   r.lower();          //lower method is made use of in lowering the arm
				   d++;
			   }

	r.pick();  // pick method is used to pick the blocks from the temp Column
			   
	while(d>0)

	{
			r.raise();        // raise method is used to raise the arm
		    d--;              //depth value decreases for every iteration
	}
	while(w>TargetColumn)
		   
	{
		   r.contract();         // contract method helps to contract the arm
		   w--;
	}

	while(h-1-temp[2]-d>TargetColumnSize)
	{
		   r.lower();           //lower method is made use of in lowering the arm
		   d++;
		   
	}

	r.drop(); // drop method is used to drop the block

	while(d>0)
	{
		   r.raise();           // raise method is used to raise the arm
		   d--;                  //depth value decreases for every iteration
	}

	TargetColumnSize+=temp[2];
		 }
	
	 
		 
 // Picking the Second Block   
	int   SourceHeight3=SourceHeight-FourthBlockHeight-ThirdBlockHeight;
		   
	int  Source3=max-(SourceHeight3	- SecondBlockHeight);
			
	if(Source3<constraint)
			{
				constraint=constraint-1;
			}
				
	while(h<constraint)
		
	        {
				   
			     r.up();             // up method is utilized to increase the height of the arm
				 h++;             //height value increases for every iteration
			}
			      
    while(w<SourceColumnPosition)
			   
            {
				   r.extend();     //extend method is utilized for moving to the source column.
				   w++;              //width increases for every iteration
			}
			    
	while(h  - 1 -  d > SourceHeight3 )
			   
	        {
				   r.lower();          //lower method is made use of in lowering the arm
				   d++;
			 }
			   
	r.pick();     // pick method is used to pick the blocks from the Source Column
			   
	while(d>0)
	
	         {
				   r.raise();         // raise method is used to raise the arm
				   d--;                //depth value decreases for every iteration
			 }
	//If my required third block is the same as the second block in the source column.
	if(required[2]==blockHeights[1])
	{		   
	while(w>TargetColumn)
			   {
				   r.contract();        // contract method helps to contract the arm
				   w--;
			   }
			   
    while(h-1-SecondBlockHeight-d>TargetColumnSize)
			   
               {
				   r.lower();            //lower method is made use of in lowering the arm
				   d++;
			   }
			 
    r.drop(); // drop method is used to drop the block
			 
	while(d>0)
	       
	       {
				   r.raise();          // raise method is used to raise the arm
				   d--;                 //depth value decreases for every iteration
		   }
			   
	TargetColumnSize+=SecondBlockHeight;}
	//If my required second block is the same as the second block in the source column.
	else if(required[1]==blockHeights[1])
	{		   
	while(w>TargetColumn)
			   {
				   r.contract();        // contract method helps to contract the arm
				   w--;
			   }
			   
    while(h-1-SecondBlockHeight-d>TargetColumnSize)
			   
               {
				   r.lower();             //lower method is made use of in lowering the arm
				   d++;
			   }
			 
    r.drop(); // drop method is used to drop the block
			 
	while(d>0)
	       
	       {
				   r.raise();           // raise method is used to raise the arm
				   d--;                  //depth value decreases for every iteration
		   }
			   
	TargetColumnSize+=SecondBlockHeight;}
	//If my required first block is the same as the second block in the source column.
	else if(required[0]==blockHeights[1])
	{		   
	while(w>TargetColumn)
			   {
				   r.contract();
				   w--;
			   }
			   
    while(h-1-SecondBlockHeight-d>TargetColumnSize)
			   
               {
				   r.lower();               //lower method is made use of in lowering the arm
				   d++;
			   }
			 
    r.drop(); // drop method is used to drop the block
			 
	while(d>0)
	       
	       {
				   r.raise();             // raise method is used to raise the arm
				   d--;                  //depth value decreases for every iteration
		   }
			   
	TargetColumnSize+=SecondBlockHeight;}
// moving the block to temp column.	
	else {
		while(w>TempColumn)
			   
	       {
			   r.contract();        // contract method helps to contract the arm
			   w--;
		   }
		   
		while(h-1-blockHeights[1]-d>TempColumnSize)
		   {
			   r.lower();              //lower method is made use of in lowering the arm
			   d++;
			   
		   }
		 
		r.drop(); // drop method is used to drop the block
		 
		while(d>0)
		   {
			   r.raise();          // raise method is used to raise the arm
			   d--;                  //depth value decreases for every iteration
		   }
		   
		TempColumnSize+=blockHeights[1];
		temp[0]=blockHeights[1];}
	//If my required third block is in temp column as it's first block.
	if(required[2]==temp[0])
	{
		while(h<constraint)
			   
        {
		       r.up();   // up method is utilized to increase the height of the arm
			   h++;     //height value increases for every iteration
		   }
		      
while(w<TempColumn)
		   
        {
			   r.extend();        //extend method is utilized for moving to the temp column.
			   w++;              //width increases for every iteration
		   }
		    
while(h  - 1 -  d > TempColumnSize )
		   
        {
			   r.lower();          //lower method is made use of in lowering the arm
			   d++;
		   }

r.pick();          // pick method is used to pick the blocks from the temp Column
		   
while(d>0)

{
		r.raise();        // raise method is used to raise the arm
	    d--;               //depth value decreases for every iteration
}
while(w>TargetColumn)
	   
{
	   r.contract();            // contract method helps to contract the arm
	   w--;
}

while(h-1-temp[0]-d>TargetColumnSize)
{
	   r.lower();               //lower method is made use of in lowering the arm
	   d++;
	   
}

r.drop(); // drop method is used to drop the block

while(d>0)
{
	   r.raise();            // raise method is used to raise the arm
	   d--;                  //depth value decreases for every iteration
}

TargetColumnSize+=temp[0];
	 }
	//If my required third block is in temp column as it's second block.
	else if(required[2]==temp[1])
	{
		while(h<constraint)
			   
        {
		       r.up();  // up method is utilized to increase the height of the arm
			   h++;      //height value increases for every iteration
		   }
		      
while(w<TempColumn)
		   
        {
			   r.extend();        //extend method is utilized for moving to the temp column.
			   w++;                   //width increases for every iteration
		   }
		    
while(h  - 1 -  d > TempColumnSize )
		   
        {
			   r.lower();        //lower method is made use of in lowering the arm
			   d++;
		   }

r.pick();         // pick method is used to pick the blocks from the temp Column
		   
while(d>0)

{
		r.raise();           // raise method is used to raise the arm
	    d--;                  //depth value decreases for every iteration
}
while(w>TargetColumn)
	   
{
	   r.contract();             // contract method helps to contract the arm
	   w--;
}

while(h-1-temp[1]-d>TargetColumnSize)
{
	   r.lower();              //lower method is made use of in lowering the arm
	   d++;
	   
}

r.drop(); // drop method is used to drop the block

while(d>0)
{
	   r.raise();         // raise method is used to raise the arm
	   d--;                 //depth value decreases for every iteration
}

TargetColumnSize+=temp[1];
	 }
	
 
			   
//Picking the Bottom Block - First Block    
	int   SourceHeight4=SourceHeight-FourthBlockHeight-ThirdBlockHeight-SecondBlockHeight;
			   
    int  Source4=max-(SourceHeight4	- FirstBlockHeight);
				
    if(Source4<constraint)
				{
					constraint=constraint-1;
				}
					
	while(h<constraint)
				   
	               {
				       r.up();   // up method is utilized to increase the height of the arm
					   h++;      //height value increases for every iteration
				   }
				      
	while(w<SourceColumnPosition)
				   
	               {
					   r.extend();       //extend method is utilized for moving to the source column.
					   w++;              //width increases for every iteration
				   }
				    
    while(h  - 1 -  d > SourceHeight4 )
				   
                   {
					   r.lower();         //lower method is made use of in lowering the arm
					   d++;
				   }

    r.pick();        // pick method is used to pick the blocks from the Source Column
				   
	while(d>0)
		
	      {
				r.raise();       // raise method is used to raise the arm
			    d--;           //depth value decreases for every iteration
		  }
	//If my required fourth block is the same as the first block in the source column.
	if(required[3]==blockHeights[0])
	{			   
	while(w>TargetColumn)
				   
	              {
					   r.contract();           // contract method helps to contract the arm
					   w--;
				   }
				   
    while(h-1-FirstBlockHeight-d>TargetColumnSize)
				   
                  {
					   r.lower();            //lower method is made use of in lowering the arm
					   d++;
				   }
				 
	r.drop(); // drop method is used to drop the block
				 
	while(d>0)
				
	              {
					   r.raise();          // raise method is used to raise the arm
					   d--;                //depth value decreases for every iteration
				   }
				   
	TargetColumnSize+=FirstBlockHeight;}
	//If my required third block is the same as the first block in the source column.
	else if(required[2]==blockHeights[0])
	{			   
	while(w>TargetColumn)
				   
	              {
					   r.contract();        // contract method helps to contract the arm
					   w--;
				   }
				   
    while(h-1-FirstBlockHeight-d>TargetColumnSize)
				   
                  {
					   r.lower();           //lower method is made use of in lowering the arm
					   d++;
				   }
				  
	r.drop(); // drop method is used to drop the block
				 
	while(d>0)
				
	              {
					   r.raise();          // raise method is used to raise the arm
					   d--;                  //depth value decreases for every iteration
				   }
				   
	TargetColumnSize+=FirstBlockHeight;}
	//If my required second block is the same as the first block in the source column.
	else if(required[1]==blockHeights[0])
	{			   
	while(w>TargetColumn)
				   
	              {
					   r.contract();           // contract method helps to contract the arm
					   w--;
				   }
				   
    while(h-1-FirstBlockHeight-d>TargetColumnSize)
				   
                  {
					   r.lower();               //lower method is made use of in lowering the arm
					   d++;
				   }
				 
	r.drop(); // drop method is used to drop the block
				 
	while(d>0)
				
	              {
					   r.raise();          // raise method is used to raise the arm
					   d--;                //depth value decreases for every iteration
				   }
				   
	TargetColumnSize+=FirstBlockHeight;}
	//If my required first block is the same as the first block in the source column.
	else if(required[0]==blockHeights[0])
	{			   
	while(w>TargetColumn)
				   
	              {
					   r.contract();  // contract method helps to contract the arm
					   w--;
				   }
				   
    while(h-1-FirstBlockHeight-d>TargetColumnSize)
				   
                  {
					   r.lower();             //lower method is made use of in lowering the arm
					   d++;
				   }
				 
	r.drop(); // drop method is used to drop the block
				 
	while(d>0)
				
	              {
					   r.raise();       // raise method is used to raise the arm
					   d--;              //depth value decreases for every iteration
				   }
				   
	TargetColumnSize+=FirstBlockHeight;}
	//moving the block to temp column.
	else {
		while(w>TempColumn)
			   
	       {
			   r.contract();  // contract method helps to contract the arm
			   w--;
		   }
		   
		while(h-1-blockHeights[0]-d>TempColumnSize)
		   {
			   r.lower();             //lower method is made use of in lowering the arm
			   d++;
			   
		   }
		 
		r.drop();
		 
		while(d>0)
		   {
			   r.raise();          // raise method is used to raise the arm
			   d--;                    //depth value decreases for every iteration
		   }
		   
		TempColumnSize+=blockHeights[0];}
	
	//If my required fourth block is in temp column as it's first block.
	if(required[3]==temp[0])
	{
		while(h<constraint)
			   
        {
		       r.up();     // up method is utilized to increase the height of the arm
			   h++;       //height value increases for every iteration
		   }
		      
while(w<TempColumn)
		   
        {
			   r.extend();        //extend method is utilized for moving to the temp column.
			   w++;                    //width increases for every iteration
		   }
		    
while(h  - 1 -  d > TempColumnSize )
		   
        {
			   r.lower();              //lower method is made use of in lowering the arm
			   d++;
		   }

r.pick();          // pick method is used to pick the blocks from the temp Column
		   
while(d>0)

{
		r.raise();           // raise method is used to raise the arm
	    d--;                //depth value decreases for every iteration
}
while(w>TargetColumn)
	   
{
	   r.contract();  // contract method helps to contract the arm
	   w--;
}

while(h-1-temp[0]-d>TargetColumnSize)
{
	   r.lower();            //lower method is made use of in lowering the arm
	   d++;
	   
}

r.drop(); // drop method is used to drop the block

while(d>0)
{
	   r.raise();   // raise method is used to raise the arm
	   d--;
}

TargetColumnSize+=temp[0];
	 }
 
		 
	 
 } 
 }
 
 
 
 

  





	   
   


