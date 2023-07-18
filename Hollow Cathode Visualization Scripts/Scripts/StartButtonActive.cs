using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class StartButtonActive : MonoBehaviour
{
    public void StartVisualization()
    {
        MenuButtons.CC_Enabled = FindObjectOfType<Toggle>().isOn;
        SceneManager.LoadScene("Activate Cathode");
    }
}
