export interface ILogResponse{
  totalRequest:number;
	requestOk:number;
	request4xx:number;
	request5xx:number;
	averageRespTime:number;
	minResptime:number;
	maxResptime:Number;
}
