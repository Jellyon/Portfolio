using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class camSwitch : MonoBehaviour
{
    public GameObject stopBtn; //adds a button to end the tour camera lock
    void Start(){
        this.hidebtn();
    }
    
    // Start is called before the first frame update
    public void showbtn(){
        stopBtn.SetActive(true);
    }
    public void hidebtn(){
        stopBtn.SetActive(false);
    }
}
