#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(void)
{
	int choice;
	char *args[]={"ps",NULL};
	pid_t pid1, pid2;
	char *command[]={"ls",NULL};
	int pid;
	int status;
	char *join[]={"join","file1","file2",NULL};
	puts("UNIX COMMANDS EXECUTION");
	
	do
	{
		printf("Menu\n1. PS\n2. JOIN\n3. FORK\n4. WAIT\n5. EXEC\n0. Exit\n\nEnter your choice: ");
		scanf("%d",&choice);
		switch(choice)
		{
			case 1:
				execvp(args[0],args);
				sleep(3);
				break;
			case 2:
				execvp(join[0],join);
				sleep(3);
				break;
			case 3:
				printf("FORK AND WAIT DEMO\n");
				printf("Parent PID: %d\n",getpid());
				pid1 = fork();
				if(pid1==0)
				{
					printf("\n1 CHILD FORKED PROCESS PID: %d\n",getpid());
					sleep(2);
					printf("1 CHILD EXIT \n\n");
					exit(1);
				}
				printf("PID AT END: %d\n",getpid());
				sleep(3);
				break;
			case 4:
				pid = fork();
				int varlcl=0;
				int vargbl;
				if(pid==0)
				{
					vargbl++;
					varlcl++;
					printf("Child process PID: %d",getpid());
					printf("\n");
					printf("Child process :: var_lcl [%d], var_gbl [%d]", varlcl, vargbl, "\n");
					printf("\n");
				}
				else if(pid>0)
				{
					printf("Parent process PID: %d",getpid());
					printf("\n");
					varlcl=10;
					vargbl=20;
					printf("Parent process :: var_lcl [%d], var_gbl [%d]", varlcl, vargbl, "\n");
					printf("\nChild process exit code: %d\n",WIFEXITED(status));
					sleep(3);
					wait(&status);
				}
				break;
			case 5:
				execvp(command[0],command);
				printf("\n");
				break;
		}
	}
	while (choice!=0);
	return EXIT_SUCCESS;
}
