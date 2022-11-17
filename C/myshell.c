#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>

int* fd;
int a =0; //using this to test return values
int waitstat;
pid_t pid; //process ID variables
char* path = NULL, *tempPath, *tempPath2;

//parent = getpid();

void printDirectory()
{

	int chdir(const char* path);
	char s[100];

	
	printf("%s\n", getcwd(s, 100));
	
}
int changeDirectory()
{
	
	chdir("..");
   printDirectory();
	return 0;
}


void commands(char* c[], int len) {

	if (strcmp(c[0], "exit") == 0) {
		exit(1);
	}


	else if (strcmp(c[0], "cd") == 0) {
		//run our directory command
         if(len == 1)
            changeDirectory();
         else if((a = chdir(c[1]))==0){
            printDirectory();
}
         else
            printf("path not found\n");
         c = NULL;
	}
   
	else if (strcmp(c[0], "path") == 0) {
		//run our path command
		//printf("our path command\n");
      if (len == 1){
         if(path == NULL)
            printf("path is empty, use path +\n");
         else
            printf("%s\n", path);
        c = NULL;
      }
      
		else if (len == 2) {
			if (c[1][0] == '+' ) {
				//printf("add c[1]\n"); //for testing
            if(path == NULL){
               path = &c[1][1];
               c = NULL;
            }
            else{
               tempPath = (char*)malloc(sizeof(path) + sizeof(c[1]));
               tempPath = path;
               path = NULL;
               path = (char*)malloc(sizeof(tempPath));
               strcat(tempPath, &c[1][1]);
               path = tempPath;
               tempPath=NULL;
               c = NULL;
            }
			}
         else if(c[1][0] == '-'){
            //printf("remove c[1]\n"); //for testing
            if(path == NULL){
               printf("path is empty\n");
               c = NULL;
               }
            else if(strstr(path, &c[1][1]) == NULL){
               printf("%s is not in path", &c[1][1]);
            }
            else{
               tempPath = &c[1][1];
               tempPath2 = (char*)malloc(sizeof(strlen(tempPath)));
               tempPath2 = strstr(path, tempPath);
               tempPath2[0] = '\0';
               tempPath2 += strlen(tempPath);
               strcat(path, tempPath2);
               //printf("%stest\n%stest\n%stest\n",tempPath, tempPath2, path);
               tempPath = NULL;
               tempPath2 = NULL;
               c = NULL;     
            }
         }
         else if(strcmp(c[1], "clear")==0){
            path = NULL;
            c = NULL;
         }   
		}
      else if (len ==3)
         printf("too many arguments");
      else
         printf("Invalid arguments");
      c = NULL;
	}


	else {
		/*our fork child process
		
		*/
   if ((pid = fork()) == -1){
         printf("error\n");
         exit(1);
      }
   else if (pid == 0){
         //printf("Running child process. ID: %ld\n", (long)getpid()); //this line used for testing
         
    if(strstr(path, c[0])!= NULL){
         
         if(len == 1){
            if(execl(path, c[0], NULL)==-1){ 
               printf("invalid command. check path\n");
               exit(1);
            }
         }
         else if(len == 2){
            if(execl(path, c[0], c[1], NULL)==-1){ 
               printf("invalid command. check path\n");
               exit(1);
            }
         }
         else if(len == 3){
            if(execl(path, c[0], c[1], c[2], NULL)==-1){ 
               printf("invalid command. check path\n");
               exit(1);
            }
         }
         
         else if(len == 4){
            if(execl(path, c[0], c[1], c[2], c[3], NULL)==-1){ 
               printf("invalid command. check path\n");
               exit(1);
            }
         }
         else if(len == 5){
            if(execl(path, c[0], c[1], c[2], c[3], c[4], NULL)==-1){ 
               printf("invalid command. check path\n");
               exit(1);
            }
         }
         else if(len >5){
         printf("too many arguments for our shell to process\n");
         exit(1);
         }
      }
      else
         printf("path doesn't match command.\n");
         
  exit(0); //THIS WILL EXIT THE CHILD PROCESS
 }     
    else do{//wait until child is done, begins parent process, which is basically just restart the main while loop
      if((pid = waitpid(pid, &waitstat, WNOHANG)) < 0){
         printf("error");
         exit(1);
      }
      else{
         if (WIFEXITED(waitstat));
      }  
    }
    while(pid == 0);   
	}   
}//end commands

void pipeCommand(char** str){
   //variables needed to pipe():
   int pipeDescriptor[2];
   pid_t pid1, pid2, pid3;
   
   printf("pipe not yet implemented\n");
   //printf("%s%s%s\n", str[0], str[1], str[2]);//this line tests the strings
   
   for(int i=0; i<3; i++){
      if(str[i] == NULL)
         break;
      /*//this is where we need to implement the pipe:VVVVV
         if(pipe(pipeDescriptor) < 0){
            printf("Pipe failure\n");
            return;
         }   
         pid1 = fork();
         if(pid1 < 0){//failed fork()
            printf("Fork failure\n");
            return;
         }
         else if(pid1 == 0){//successful fork
            close(pipeDescriptor[0]);
            dup2(pipeDescriptor[1], STDOUT_FILENO);
            close(pipeDescriptor[1]); 
            
               //need to call commands(str[i]); but I can't parse the pipe string properly
               //this is hopefully close!
               //get the number of args (len) like we need to pass into commands(str, len)
         }
      */
   }
}//end pipeCommand 

int main(int argc, char* argv[]) {

	int commandLength;
	fd = malloc(sizeof(int));
	char prompt[] = "Our Simple Shell, type your commands below\n";
	char* input = malloc(50), * token, * delim = " ", * buffer , * buffer2;
	//our pointers and size
	char** command = (char**)malloc(2 * sizeof(char*));
	for (int i = 0; i < 2; i++)
		command[i] = (char*)malloc(50 * sizeof(char*));
	printf("%s", prompt);
   
	//our main loop for the shell
	while (1) {
		//take our input    
      printf("shell: ");
		fgets(input, 50, stdin);
		//resetting the command length
		buffer = strdup(input);
      buffer[strlen(buffer)-1] = '\0';
		commandLength = 0;

		//parseing our input 
      //chek for pipe first
      buffer2 = buffer;
      char** pipeStr = (char**)malloc(sizeof(buffer));
      for(int i=0; i<3; i++){
         pipeStr[i] = strsep(&buffer2, "|");
         if(pipeStr[i] == NULL)
            break;
      }
      if(pipeStr[1] != NULL){
         pipeCommand(pipeStr);
      }
      else{
		   while ((token = strsep(&buffer, delim)) != NULL) {
			   if (token[strlen(token) - 1] == '\n')
				   token[strlen(token) - 1] = '\0';
			command[commandLength] = token;
			commandLength++;
		}

		commands(command, commandLength);
      }
	}

}
