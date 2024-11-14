public class GestionFichero {
    
    //Escribe en el fichero fFichero la cadena
    void Escribir(File fFichero,String cadena)
    {
        BufferedWriter bw;
        try
        {
            // Comprobamos si el archivo no existe y si es asi creamos uno nuevo.
         if(!fFichero.exists())
         {
             fFichero.createNewFile();
         }

           // Luego de haber creado el archivo procedemos a escribir dentro de el.
            bw = new BufferedWriter(new FileWriter(fFichero,true));
            bw.write(cadena);
            bw.close();

        }catch(Exception e)
        {
            System.out.println(e);
        }

    }

    //Borra un fichero previemente creado (Ffichero)
    void borrar (File Ffichero)
    {
        try
        {
           // Comprovamos si el fichero existe  de ser así procedemos a borrar el archivo
            if(Ffichero.exists())
            {
                Ffichero.delete();
                System.out.println("Ficherro Borrado");
            }

        }catch(Exception e)
        {
            System.out.println(e);
        }
    }

   //Modifica la cadena aCadena por nCadena en el fichero fAntiguo
    void modificar(File fAntiguo,String aCadena,String nCadena)
    {
       
      //La dos lineas siguientes, generan un fichero nuevo de nombre el antiguo + aux + numero aleatorio
        Random numaleatorio = new Random(3816L);
        String nFnuevo = fAntiguo.getParent()+"/auxiliar"+String.valueOf(Math.abs(numaleatorio.nextInt()))+".txt";

     // Creo un nuevo archivo
        File fNuevo= new File(nFnuevo);
        BufferedReader br;
        try
        {
            
            if(fAntiguo.exists())
            {
                br = new BufferedReader(new FileReader(fAntiguo));

                String linea;
                while((linea=br.readLine()) != null)
                {
                    if(linea.equals(aCadena))
                    {
                        Escribir(fNuevo,nCadena);

                    }
                    else
                    {
                        Escribir(fNuevo,linea);
                    }
                }
                br.close();
                String nAntiguo = fAntiguo.getName();
                borrar(fAntiguo);
                fNuevo.renameTo(fAntiguo);

            }
            else
            {
                System.out.println("Fichero no Existe");
            }

        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
}