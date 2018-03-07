#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <getopt.h>
#include <netinet/in.h>
#include "debug.h"
#include "socket.h"

#define USAGE   "USAGE:\n"\
                "      ./server -p <listen_port> -d <root_dir>\n" 

void opt(int argc, char *argv[]);


int port = 8080;
char root_dir[128] = "/tmp/";


int main (int argc, char *argv[])
{
    int listenfd, connfd;
    struct sockaddr_in clientaddr;
    unsigned int clientlen;

    opt(argc, argv);

    setbuf(stdout, NULL);

    listenfd = open_listenfd(port);

    while (1)
    {
        clientlen = sizeof(clientaddr);
        connfd = accept(listenfd, (struct sockaddr*)&clientaddr, &clientlen);
        debug_info("get an conn\n");
        if (connfd < 0)
        {
            perror("accept");
            exit(-1);
        }

        close(STDIN_FILENO);
        close(STDOUT_FILENO);
        close(STDERR_FILENO);
        
        dup2(connfd, STDIN_FILENO);
        dup2(connfd, STDOUT_FILENO);
        dup2(connfd, STDERR_FILENO);

        fprintf(stdout, "abc\n");
    }

    return 0;
}


void opt(int argc, char *argv[])
{
    int opt;
    while ((opt = getopt(argc, argv, "d:p:")) != -1)
    {
        switch (opt)
        {
        case 'd':
            debug_trace("%c, %s\n", opt, optarg);
            strncpy(root_dir, optarg, 128);
            break;
        case 'p':
            debug_trace("%c, %s\n", opt, optarg);
            port = atoi(optarg);
            break;
        case '?':
            debug_error("option error");
            debug_error("%s", USAGE);
            exit(-1);
            break;
        default:
            debug_trace("default %d\n", opt);
            break;
        }
    }
}


