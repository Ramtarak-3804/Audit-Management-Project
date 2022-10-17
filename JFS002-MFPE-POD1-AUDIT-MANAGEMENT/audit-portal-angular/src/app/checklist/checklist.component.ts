import { GetQuestionsList } from './../Services/getQuestionList.service';
import { Severity } from './../Services/severity.service';
import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Security } from '../Services/security.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { QuesURL } from '../Models/tokens';

@Component({
  selector: 'app-checklist',
  templateUrl: './checklist.component.html',
  styleUrls: ['./checklist.component.css']
})

export class ChecklistComponent implements OnInit {

  constructor(private qList:GetQuestionsList,
    private sev:Severity , private severityapi:Severity,
    private route:Router,
    private security:Security,
    private http:HttpClient,
    @Inject(QuesURL) private queSerUrl:string
    ) { }

    public message:string="";
    public url:string="http://54.166.244.11:8000/auditChecklist/questions"
    private token=localStorage.getItem('auditToken');
    headers={
      headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization':`Bearer ${this.token}`
      })
    }
  
  static getAuditTypeVal(): string {
    throw new Error('Method not implemented.');
  }

  public auditType:string="";
  public projectName:string="";
  showQues:boolean=false;
  public quesList:any[]=[];
  public loadFlag=false;
  public response:{questionId:number,question:string,auditType:string, response:string}[]=[];

  ngOnInit(): void {
    if(!this.security.checkLogin())
    this.route.navigate(['unauthorisedError']);

    this.loadFlag=false;
    console.log("firing");
    this.response=this.qList.getQuestionList();
    this.emptyResponse();
  }

  getQuestions(){
    console.log(this.auditType);
    console.log(this.projectName);

    if(this.auditType.length>0 && this.projectName.length>0){
      this.getList();
    }

    else
    alert("Please Enter details correctly");

  }
  
   getList(){
     this.loadFlag=true;
     let temp:any;
    this.http.get(this.url+"/"+this.auditType,this.headers).subscribe(response=>{
      console.log(response);
      temp=response;
    },
    error=>{
      if(error.status=="401"){
        this.route.navigate(['server-error']);
      }
      else{
        alert("uncexpected error occured");
        this.route.navigate(['serverError']);
      }
    },
    ()=>{
      this.quesList=temp;
    });
  }

  responseYes(i:number){
    this.response[i].questionId=this.quesList[i].questionId;
    this.response[i].question = this.quesList[i].question;
    this.response[i].auditType = this.quesList[i].auditType;
    this.response[i].response="YES";
    console.log(typeof this.response)
  }

  responseNo(i:number){
    this.response[i].questionId=this.quesList[i].questionId;
    this.response[i].question = this.quesList[i].question;
    this.response[i].auditType = this.quesList[i].auditType;
    this.response[i].response="NO";
  }

  getResponse(){
    if(this.qList.validateResponse(this.response)){
    this.qList.setQuestionList(this.response);
    this.qList.setDetails(this.projectName,this.getAuditTypeVal());
    this.route.navigate(['dashboard']);
    }
    else{
      this.message="Please Answer all the questions!";
    }
  }

  emptyResponse(){
    this.response[0].response="";
    this.response[1].response="";
    this.response[2].response="";
    this.response[3].response="";
    this.response[4].response="";
  }

  getAuditTypeVal(){
    if(this.auditType==="Internal")
      return 0;
    else
      return 1;
  }

}
