export class AuditResponse{
    constructor(
        public auditResponseId:number,
        public projectName:string,
        public managerName:string,
        public projectExecutionStatus:string,
        public remedialActionDuration:string
    ){}
}