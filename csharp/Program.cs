using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using System.Data.OleDb;
using System.Windows;
using System.Windows.Forms;

namespace ExempleCoursLFI2
{
    class DBConnection
    {        
        public static OleDbConnection GetConnection()
        {
            string strcnn = @"Provider=Microsoft.ACE.OLEDB.12.0;Data Source=OuvragesDB.accdb; Persist Security Info=False";
            return new OleDbConnection(strcnn);
        }
    }
    public class Ouvrage
    {
        public int Inv;
        public string Titre;
        public string Auteur;
        public int AnneeEdition;
        public DateTime DateAchat;

        public Ouvrage()
        {

        }

        public Ouvrage(int i, string t, string a, int ae, DateTime da)
        {
            Inv = i;
            Titre = t;
            Auteur = a;
            AnneeEdition = ae;
            DateAchat = da;
        }
     }

    class DALOuvrage
    {
        public static void Insert(Ouvrage ouv)
        {
            OleDbConnection cnn = DBConnection.GetConnection();
            cnn.Open();
            string strsql = @"INSERT INTO Ouvrage VALUES(?,?,?,?,?)";
            OleDbCommand cmd = new OleDbCommand(strsql, cnn);            

            OleDbParameter pInv = new OleDbParameter("pInv", ouv.Inv);
            OleDbParameter pTitre = new OleDbParameter("pTitre", OleDbType.VarChar);
            pTitre.Value = ouv.Titre;

            OleDbParameter pAuteur = new OleDbParameter("pAuteur", OleDbType.VarChar);
            pAuteur.Value = ouv.Auteur;

            OleDbParameter pAnneeEdition = new OleDbParameter("pAnneeEdition", OleDbType.Integer);
            pAnneeEdition.Value = ouv.AnneeEdition;

            OleDbParameter pDateAchat = new OleDbParameter("pDateAchat", OleDbType.Date);
            pDateAchat.Value = ouv.DateAchat;

            cmd.Parameters.AddRange(new OleDbParameter[]{ pInv, pTitre, pAuteur, pAnneeEdition, pDateAchat });
         
            cmd.ExecuteNonQuery();
            cnn.Close();
         }

        public static void Delete(int Inv)
        {
            OleDbConnection cnn = DBConnection.GetConnection();
            cnn.Open();
            string strsql = @"DELETE FROM Ouvrage WHERE Inventaire = ?";
            OleDbCommand cmd = new OleDbCommand(strsql, cnn);

            OleDbParameter pInv = new OleDbParameter("pInv", Inv);
            
            cmd.Parameters.Add(pInv);
            cmd.ExecuteNonQuery();
            cnn.Close();
        }

        public static void Update(int OldInv, Ouvrage newOuv)
        {
            OleDbConnection cnn = DBConnection.GetConnection();
            cnn.Open();
            string strsql = @"UPDATE Ouvrage SET Inventaire =?, Titre =?, Auteur =?, AnneeEdition=?, DateAchat = ? WHERE Inventaire = ?";

            OleDbCommand cmd = new OleDbCommand(strsql, cnn);

            cmd.Parameters.Add(new OleDbParameter("pNewInv", newOuv.Inv));
            cmd.Parameters.Add(new OleDbParameter("pTitre", newOuv.Titre));
            cmd.Parameters.Add(new OleDbParameter("pAuteur", newOuv.Auteur));
            cmd.Parameters.Add(new OleDbParameter("pAnneeEdition", newOuv.AnneeEdition));
            cmd.Parameters.Add(new OleDbParameter("pDateAchat", newOuv.DateAchat));
            cmd.Parameters.Add(new OleDbParameter("pOldInv", OldInv));
            cmd.ExecuteNonQuery();
            cnn.Close();
        }

        public static Ouvrage SelectByInv(int Inv)
        {
            Ouvrage ouv = new Ouvrage();
            OleDbConnection cnn = DBConnection.GetConnection();
            cnn.Open();
            string strsql = @"SELECT * FROM Ouvrage WHERE Inventaire = ?";
            OleDbCommand cmd = new OleDbCommand(strsql, cnn);
            cmd.Parameters.Add(new OleDbParameter("pInv", Inv));
            OleDbDataReader rd = cmd.ExecuteReader();

            if (rd!=null)
            {
                if(rd.Read())
                {
                    ouv.Inv = rd.GetInt32(0);
                    ouv.Titre = rd.GetString(1);
                    ouv.Auteur = rd.GetString(2);
                    ouv.AnneeEdition = rd.GetInt32(3);
                    ouv.DateAchat = rd.GetDateTime(4);              
                }
            }
            return ouv;
        }

        public static List<Ouvrage> SelectAll()
        {
            List<Ouvrage> LesOuvrages = new List<Ouvrage>();
            OleDbConnection cnn = DBConnection.GetConnection();
            cnn.Open();
            string strsql = @"SELECT * FROM Ouvrage";
            OleDbCommand cmd = new OleDbCommand(strsql, cnn);
            OleDbDataAdapter DA = new OleDbDataAdapter(strsql, cnn);
            DataTable dt = new DataTable("Ouvrage");
            DA.Fill(dt);
            foreach (DataRow dr in dt.Rows)
            {
                LesOuvrages.Add(new Ouvrage((int)dr[0], dr[1].ToString(), dr[2].ToString(), (int)dr[3], (DateTime)dr[4]));                                                                                        
            }
            return LesOuvrages;
        }

        //public static List<Ouvrage> SelectByCriterium(string FieldName, string FieldValue)
        //{
        //}
    }

    class UserConsoleInterface
    {
        public static Ouvrage GetOuvrageFromInterface()
        {
            Ouvrage ouv = new Ouvrage();
            Console.WriteLine("\n **** Saisie d'un ouvrage **** ");
            Console.Write(" Inventaire  : ");
            ouv.Inv = Int32.Parse(Console.ReadLine());
            Console.Write(" Titre  : ");
            ouv.Titre = Console.ReadLine();
            Console.Write(" Auteur  : ");
            ouv.Auteur = Console.ReadLine();
            Console.Write(" Année d'édition  : ");
            ouv.AnneeEdition = Int32.Parse(Console.ReadLine());
            Console.Write(" Date d'achat  : ");
            ouv.DateAchat = DateTime.Parse(Console.ReadLine());            
            return ouv;
        }

        public static void ShowOuvrage(Ouvrage ouv)
        {
            Console.Write("\n Inventaire : " + ouv.Inv);
            Console.Write("\n Titre : " + ouv.Titre);
            Console.Write("\n Auteur : " + ouv.Auteur);
            Console.Write("\n Année d'édition : " + ouv.AnneeEdition);
            Console.Write("\n Date d'achat : " + ouv.DateAchat.ToShortDateString());            
        }

        static char ChoixAction()
        {
            char Choix=' ';
            do
            {
                Console.Clear();
                Console.WriteLine( "***************************************************** \n");
                Console.WriteLine( "                         MENU                         \n");
                Console.WriteLine( "  A : AJOUT" );
                Console.WriteLine( "  S : SUPPRESSION" );
                Console.WriteLine( "  M : MODIFICATION" );
                Console.WriteLine( "  C : CONSULTATION" );
                Console.WriteLine( "  Q : QUITTER" );

                Console.WriteLine( "\n*****************************************************" );
                Console.Write( "Donner votre choix : ");
                string str = Console.ReadLine();
                if(str.Length!=0)
                    Choix = Char.ToLower(str.Trim()[0]);
            }
            while (Choix != 'a' && Choix != 's' && Choix != 'm' && Choix != 'c' && Choix != 'q');
            return Choix;
        }

        public static void Menu()
        {
            char Choix;
            do
            {  
                Choix = ChoixAction();
                switch (Choix)
                {
                    case 'a':
                        {
                            Console.Clear();
                            Console.WriteLine( "\n************ Ajout ************" );
                            DALOuvrage.Insert(UserConsoleInterface.GetOuvrageFromInterface());                            
                            break;
                        }

                    case 's':
                        {
                            Console.Clear();
                            Console.WriteLine( "\n************ Suppression ************" );
                            Console.Write("\n\nDonner l'inventaire de l'ouvrage à supprimer : ");
                            DALOuvrage.Delete(Int32.Parse(Console.ReadLine()));                            
                            break;
                            
                        }

                    case 'm':
                        {
                            int Inv;
                            Console.Clear();
                            Console.WriteLine( "\n************ Modification ************" );
                            Console.Write("\n\nDonner l'inventaire de l'ouvrage à modifier : ");
                            Inv = Int32.Parse(Console.ReadLine());
                            UserConsoleInterface.ShowOuvrage(DALOuvrage.SelectByInv(Inv));                            
                            Console.WriteLine("\nDonner les nouvelles données de l'ouvrage :");
                            DALOuvrage.Update(Inv, UserConsoleInterface.GetOuvrageFromInterface());                            
                            break;
                        }

                    case 'c':
                        {
                            Console.Clear();
                            Console.WriteLine( "\n************ Consultation ************"); 
                            List<Ouvrage> LesOuvrages = DALOuvrage.SelectAll();
                            foreach (Ouvrage ouv in LesOuvrages)
                            {
                                Console.WriteLine("\n \n ------------------------- ");
                                UserConsoleInterface.ShowOuvrage(ouv);
                            }
                            Console.Read();
                            break;
                        }
                }
            } while (Choix != 'q');
        }
    }

    //class UserFormInterface : Form
    //{
    //    Label LbInv;
    //    TextBox TxtInv;
    //    Label LbDesignation;
    //    TextBox TxtDesignation;
    //    Button BnAjouter;
    //    public UserFormInterface()
    //    {
    //        this.TxtInv = new TextBox();
    //        this.TxtInv.Location = new System.Drawing.Point(20, 75);
    //        this.TxtDesignation = new TextBox();
            
            
    //        this.LbInv = new Label();
    //        this.LbInv.Location = new System.Drawing.Point(20, 20);
    //        this.LbInv.Text = "Inventaire";
            
    //        this.LbDesignation = new Label();
    //        this.LbDesignation.Text = "Désignation";
            
    //        this.BnAjouter = new Button();
    //        this.BnAjouter.Text = "Ajouter";

    //        this.Controls.Add(this.TxtInv);
    //        this.Controls.Add(this.TxtDesignation);
    //        this.Controls.Add(this.LbInv);
    //        this.Controls.Add(this.LbDesignation);
    //        this.Controls.Add(this.BnAjouter);

    //    }
        
    //}
    class Program
    {
    

        static void Main(string[] args)
        {           
            UserConsoleInterface.Menu();            
        }
    }
}
