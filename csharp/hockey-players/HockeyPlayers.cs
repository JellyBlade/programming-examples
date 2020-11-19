using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;

namespace HockeyPlayers
{
    public partial class HockeyPlayers : Form
    {
        public HockeyPlayers()
        {
            InitializeComponent();

            button3.Enabled = false;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (textBox1.TextLength != 0 && textBox2.TextLength != 0 && textBox3.TextLength != 0)
            {
                HockeyPlayer newPlayer = new HockeyPlayer(textBox1.Text, int.Parse(textBox2.Text), int.Parse(textBox3.Text));
                listBox1.Items.Add(newPlayer);

                cleanUp();
            }

        }

        private void cleanUp()
        {
            textBox1.Clear();
            textBox2.Clear();
            textBox3.Clear();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            button3.Enabled = false;
            cleanUp();
            HockeyPlayer[] tempArray = new HockeyPlayer[listBox1.Items.Count];
            listBox1.Items.CopyTo(tempArray, 0);
            Array.Sort(tempArray);

            if (checkBox1.Checked) { Array.Reverse(tempArray); }

            listBox1.Items.Clear();
            listBox1.Items.AddRange(tempArray);
        }

        private void radioButton1_CheckedChanged(object sender, EventArgs e)
        {
            if (((RadioButton)sender).Checked)
            {
                HockeyPlayer.SortOption = 0;
            }
        }

        private void radioButton2_CheckedChanged(object sender, EventArgs e)
        {
            if (((RadioButton)sender).Checked)
            {
                HockeyPlayer.SortOption = 1;
            }
        }

        private void radioButton3_CheckedChanged(object sender, EventArgs e)
        {
            if (((RadioButton)sender).Checked)
            {
                HockeyPlayer.SortOption = 2;
            }
        }

        private void listBox1_DoubleClick(object sender, EventArgs e)
        {
            button3.Enabled = true;

            HockeyPlayer selectedPlayer = (HockeyPlayer)listBox1.SelectedItem;

            textBox1.Text = selectedPlayer.playerName;
            textBox2.Text = selectedPlayer.jerseyNumber.ToString();
            textBox3.Text = selectedPlayer.goalsScored.ToString();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            HockeyPlayer modPlayer = new HockeyPlayer(textBox1.Text, int.Parse(textBox2.Text), int.Parse(textBox3.Text));

            listBox1.Items[listBox1.SelectedIndex] = modPlayer;

            button3.Enabled = false;

            cleanUp();
        }

        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            SaveFileDialog saveDialog = new SaveFileDialog();
            saveDialog.Filter = "Hockey Players data file|*.hp|Text file|*.txt";
            saveDialog.Title = "Save Hockey Players Data";
            saveDialog.ShowDialog();

            if (saveDialog.FileName != "")
            {
                FileStream save = (FileStream)saveDialog.OpenFile();

                HockeyPlayer[] saveArray = new HockeyPlayer[listBox1.Items.Count];
                listBox1.Items.CopyTo(saveArray, 0);

                BinaryFormatter binForm = new BinaryFormatter(); 
                foreach (HockeyPlayer hp in saveArray)
                {
                    binForm.Serialize(save, hp);
                }

                save.Close();

            }
        }

        private void loadToolStripMenuItem_Click(object sender, EventArgs e)
        {
            OpenFileDialog openDialog = new OpenFileDialog();
            openDialog.Filter = "Hockey Players data file|*.hp|Text file|*.txt";
            openDialog.Title = "Load Hockey Players Data";
            
            if (openDialog.ShowDialog() == DialogResult.OK)
            {

                FileStream load = (FileStream)openDialog.OpenFile();

                BinaryFormatter binForm = new BinaryFormatter();
                while (load.Position < load.Length)
                {
                    HockeyPlayer loadedPlayer = (HockeyPlayer)binForm.Deserialize(load);
                    listBox1.Items.Add(loadedPlayer);
                }

                load.Close();
            }
        }

        private void closeToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void label8_Click(object sender, EventArgs e)
        {

        }
    }

    [Serializable]
    public class HockeyPlayer : IComparable
    {
        public static int SortOption { get; set; }

        public string playerName { get; set; }
        public int jerseyNumber { get; set; }
        public int goalsScored { get; set; }


        public HockeyPlayer(string name, int jersey, int goals)
        {
            playerName = name;
            jerseyNumber = jersey;
            goalsScored = goals;
        }

        public override string ToString()
        {
            return string.Format("{0,-25} {1,-6} {2,-6}", playerName, jerseyNumber, goalsScored);
        }

        public int CompareTo(object obj)
        {
            HockeyPlayer temp = (HockeyPlayer)obj;

            switch (SortOption)
            {
                default:
                case 0:
                    return playerName.CompareTo(temp.playerName);
                case 1:
                    return jerseyNumber.CompareTo(temp.jerseyNumber);
                case 2:
                    return goalsScored.CompareTo(temp.goalsScored);
            }
        }

    }
}
