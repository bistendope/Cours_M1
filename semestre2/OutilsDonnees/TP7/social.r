social <- read.table("social.txt", header=T)
attach(social)

t.test(h,w, paired=T, conf.level=0.05)

#morale de l'histoire: échantillon trop petit pour en conclure quoi que ce soit

m <- mean(h-w)

s <- sd(h-w)

int <- s/sqrt(nrow(social))

T_right <- m + int

T_left <- m - int


