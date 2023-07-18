using System.Collections;
using System.Collections.Generic;
using UnityEngine;



public class starttour : MonoBehaviour
{
    public Camera cam, oTip, oMid, hTop, hBtm, h_iMid, iBtm, iTop, introFar, tutorial;
    public Transform insert, heater, orifice, plate;
    private Transform target;
    private Camera tourStop;
    private bool looking = true, intut=false;
    private float speed = 0.05f;
    [HideInInspector] public int tourNum = 0, tourTrip=0;
    [HideInInspector] public float limxpos, limxneg, limypos, limyneg;
    [HideInInspector] public bool tour = false; 
    [HideInInspector] public bool in_intro = false; 
    [HideInInspector] public bool moving = false;
    // Start is called before the first frame update
    
    void Start()
    {
        tour=false;
        moving=false;
    }

    public void startIntro(){
        tourNum = 4;
        target = insert;
        tourStop = introFar;
        speed = 0.75f;
        startTour();
    }
    public void continueIntro(){
        switch (tourTrip){
            case 1:
                speed = 1f;
                tourStop = h_iMid;
                break;
            case 2:
                speed = 0.8f;
                in_intro=true;
                break;
            case 3:
                in_intro = false;
                tourStop = oMid;
                speed = 0.8f;
                break;
            case 4:
                tourStop = h_iMid;
                speed = 0.9f;
                break;
            case 5:
                endTour();
                break;
        }
        moving=true;
    }
    public void startInsert(){
        //TODO: Cut all voice script and restart this one's
        tourNum = 1;
        target = insert;
        tourStop = iTop;
        speed = 0.5f;
        tour=false;
        startTour();
        
    }
    private void continueInsert(){
        switch (tourTrip) {
            case 1:
            looking = false;
                tourStop = iBtm;
                break;
            case 2:
                speed = 0.4f;
                tourStop = iTop;
                break;
            case 3:
                speed = 0.2f;
                looking = true;
                tourStop = h_iMid;
                return;
            case 4:
                tourNum=0;
                break;
        }
        moving = true;
    }
    public void startHeater(){
        tourNum = 2;
        target = heater;
        tourStop = hTop;
        speed = 0.3f;
        tour=false;
        startTour();
    }
    private void continueHeater(){
        speed = 0.25f;
         switch (tourTrip) {
            case 1:
                looking=false;
                tourStop = hBtm;
                break;
            case 2:
                tourStop = hTop;       
                break;
            case 3:
                looking=true;
                speed = 0.4f;
                tourStop = h_iMid;   
                break;
            case 4:
                 tourNum =0;
                 return;
        }
        moving = true;
    }

    public void startOrifice(){
        //TODO: Cut all voice script and restart this one's
        speed = .2f;
        tourNum = 3;
        target=orifice;
        tourStop=oTip;
        tour=false;
        startTour();
    }
    private void continueOrifice(){
        
        switch (tourTrip){
            case 1:
            speed = .8f;
                tourStop = oMid;
                break;
            case 2:
                tourStop = h_iMid;
                speed = .6f;
                break;
            case 3:
                tourNum =0;
                return;
        }
        moving = true;
    }
    public void endTour(){
        tour = false;
        moving = false;
    }
    public void startTour(){
        //TODO: hide/disable the red buttons, enable the stop tour button, which will call this.endTour()
        tourTrip = 0;
        if(tour){ 
            tour = false;
            moving = false;
        }
        else{ 
            tour = true;
            moving = true;
        }

    }

    public void startTutorial(){
        //tourNum = 5;
        intut=true;
        looking = false;
        cam.transform.position = tutorial.transform.position;
        cam.transform.rotation = tutorial.transform.rotation;
        tourTrip = 0;
    }

    public void endTutorial(){
        //tourNum = 0;
        tourTrip++;
        if (tourTrip == 6){
            cam.transform.position = h_iMid.transform.position;
            cam.transform.LookAt(insert);
        }
    }
    void setLimits(){
        limxpos = cam.transform.position.x + 1;
        limxneg = cam.transform.position.x - 1;
        limypos = cam.transform.position.y + 4;
        limyneg = cam.transform.position.y - 4;
    }

    // Update is called once per frame

    void Update()
    {
        if(!intut){
        if(tour && moving){
            if(looking){
                cam.transform.LookAt(target);
            }
            cam.transform.position = Vector3.MoveTowards(cam.transform.position, tourStop.transform.position, speed);
                
            
            if (cam.transform.position == tourStop.transform.position && moving){
                moving = false;
                setLimits();
            }
        
        }
        if(tour && !moving && !in_intro){
            
            
            switch (tourNum){
                case 0:
                    speed = .3f;
                    break;
                case 1:
                    tourTrip++;
                    
                    continueInsert();
                    break;
                case 2:
                    tourTrip++;
                    
                    continueHeater();
                    break;
                case 3:
                    tourTrip++;
                    
                    continueOrifice();
                    break;
                case 4:
                    tourTrip++;
                    continueIntro();
                    break;
                case 5:
                    break;
                    
            }
        }
        if(tour && in_intro){
            cam.transform.RotateAround(target.transform.position, new Vector3(0,1,0), speed);
            if (cam.transform.position.z > 50){
                tourTrip++;
                continueIntro();
            }  
        }
    }
    }
}