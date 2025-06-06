# Test Files

You should have full code coverage on your tests. 

- View error of "...adding container's parent to itself"

![alt text](test_imgs/image.png)

**Fix**: was callininvoking a scroll panel to conataint a list that was calling 

![alt text](test_imgs/image1.png)

- View error with JLIst selection

![alt text](test_imgs/image2.png)

- Error after adding Unit View Panel

![alt text](test_imgs/image3.png)

**Fix**: was referencing the Homes View Panel on the Units Button click

![alt text](test_imgs/image4.png)

![alt text](test_imgs/image5.png)


### Testing Clear button in add tab  
**Homes Add Tab**

Before:   
![alt text](test_imgs/image6.png)  
After:    
![alt text](test_imgs/image7.png)  

**Units Add Tab**

Before:    
![alt text](test_imgs/image8.png)
After:  
![alt text](test_imgs/image9.png)

### Testing Add button in add tab  
**Homes Add Tab**

Before:   
![alt text](test_imgs/image10.png)  
After: 
![alt text](test_imgs/image11.png)

### Testing Get Units Rows  
- Testing the get Units rows for Jtable does not have duplicates when updates are made to the files  

**Units View Tab**

**Fix:** CsvLoader had a comment for the filter when loading in the UnitItems.  
Before:   
![alt text](test_imgs/image12.png)
After:  
![alt text](test_imgs/image13.png)


### Testing Get Units Rows Displays Under Correct Columns  
- Testing the get Units rows for Jtable aligns values under correct column names
 
 Before:  
 ![alt text](test_imgs/image13.png)  
 After:  
![alt text](test_imgs/image14.png)

### Testing the Sorting for the Units Rows  
![alt text](test_imgs/image15.png)