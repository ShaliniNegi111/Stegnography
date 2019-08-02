package Stegnography ;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
class Embedder
{

         File DataFile , Vessel , Result ;
         private SecurityManager SM ;

	Embedder ( String password , String DataFile  , String Vessel , String Result ) throws Exception 
	{
	
				File f1 = new File ( DataFile ) ;
				File f2 = new File ( Vessel ) ;
				File f3 = new File ( Result ) ;
		                 if ( !f1.exists() ||  !f2.exists() )
		                 	throw new Exception ( "File does not exist" ) ;
				this.DataFile = f1 ;
				this.Vessel = f2 ;
				this.Result = f3 ;
				SM = new SecurityManager( password ) ;
				
		
	}
	
	
	public void Embed () throws Exception 
	{
		BufferedImage Image = ImageIO.read ( Vessel ) ;
		int Height = Image.getHeight() ;
		int Width = Image.getWidth() ;
		if ( Height * Width < DataFile.length() + HeaderManager.Length ) 
			throw new Exception ( "Size conflicts ") ; 
	        long  p = DataFile.length() ;
	        HeaderManager  HM = new HeaderManager( DataFile.getName() , p  )  ;
	        String Header = HM.HeaderFormation () ;
	        
	       WritableRaster WR = Image.getRaster() ;
	       
	       int r , g , b , cnt = 0 , data , flag   ;
	       boolean KeepEmbedding = true ;
	       FileInputStream srcFile = new FileInputStream(DataFile);
	       flag = SM.getFlag();
               int arr [ ] , result [ ] ;
	       
	       for ( int i = 0 ; i <  Height && KeepEmbedding == true ; i ++ )
	       {
	       		for ( int j = 0 ; j < Width ; j ++ )
	       		{
	       		
	       			r = WR.getSample ( i , j , 0 ) ;
	       			g = WR.getSample ( i , j , 1 ) ;
	       			b = WR.getSample ( i , j , 2 ) ;
	       			if ( cnt < Header.length() )
	       			{
	       			     data = Header.charAt ( cnt ) ;
	       			     cnt ++ ;
	       			}
	       			else 
	       			{
	       				data = srcFile .read() ;
	       				if ( data == -1 )
	       				{
	       					KeepEmbedding = false ;
	       					srcFile .close() ;
	       					break ;
	       				}
	       			}
	       			data = SM.Encreption ( data ) ;
	       			arr = ByteProcessor.Slice  ( data , flag ) ;
	       			result = ByteProcessor.Combine ( r , g , b , arr , flag ) ;
	       			WR.setSample ( i , j , 0 , result [ 0 ] ) ;
	       			WR.setSample ( i , j , 1 , result [ 1 ] ) ;
	       			WR.setSample ( i , j , 2 , result [ 2 ] ) ;
	       			flag = flag % 3 + 1;
	       		}
	       }
	       ImageIO.write ( Image , "PNG" , Result ) ;
		
	}
}
