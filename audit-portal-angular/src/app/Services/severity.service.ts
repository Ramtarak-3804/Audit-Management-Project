
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable, Inject} from "@angular/core";
import { Router } from '@angular/router';
import { SeverityURL } from '../Models/tokens';

import { GetQuestionsList } from './getQuestionList.service';
import { Security } from './security.service';


@Injectable({providedIn: 'root'})

export class Severity{
    private token=localStorage.getItem('auditToken');

    headers={
      headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization':`Bearer ${this.token}`
      })
    }

    constructor(private http:HttpClient,
      private route:Router,
      private qList:GetQuestionsList,
      private security:Security,
      @Inject(SeverityURL) private severityUrl:string
      ){

    }

    public url:string = "http://100.27.31.76:8200/auditSeverity";

    // requestData1=
    // {
    //   "projectName": "",
    //   "projectManagerName": "",
    //   "applicationOwnerName": "",
    //   "auditDetail": {
    //     "auditType": 0,
    //     "auditDate": "",
    //      "responses" :[
    //       {
    //         "questionId": 0,
    //         "answer": false
    //       },
    //       {
    //         "questionId": 1,
    //         "answer": false
    //       },
    //       {
    //         "questionId": 0,
    //         "answer": false
    //       },
    //       {
    //         "questionId": 0,
    //         "answer": false
    //       },
    //       {
    //         "questionId": 0,
    //         "answer": true
    //       }
    //     ]
    //   }
    // };

    requestData1={
      "auditRequestId":102,
      "projectName":"proj 2",
      "managerName":"maddy",
      "auditDetail":{
        "auditDate":"2021-09-25",
        "auditQuestions":[
          {
            "questionId":1,
            "question":"question 1",
            "auditType":"SOX",
            "response":"no"
          },
          {
            "questionId":2,
            "question":"question 2",
            "auditType":"SOX",
            "response":"no"
          }
          
          ]
      }
    };
    
    setDetails(){
      this.requestData1.auditRequestId = Math.random();
      this.requestData1.projectName=this.qList.getProjectName(),
      this.requestData1.managerName=this.security.getUserName(),
      this.requestData1.auditDetail.auditQuestions=this.qList.getQuestionList();
      this.requestData1.auditDetail.auditDate="25-09-2022";
      //this.requestData1.auditDetail.responses=this.getResponses(this.qList.sendResponse());
    }

    // getResponses(res:{questionId:number, answer:string}[]){
    //   var temp:{questionId:number, answer:boolean}[]=[];

    //   for(var result of res){
    //     if(result.answer==="YES")
    //     temp.push({questionId:result.questionId,answer:true});
    //     else
    //     temp.push({questionId:result.questionId,answer:false}); 
    //   }
    //   return temp;
    // }

    getTokFromlocal(){
      return this.token;
    }


    public executionStatus(){
      this.setDetails();
      return this.http.post(this.url+"/severity",this.requestData1,this.headers);
    }

}
