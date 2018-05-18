ozoneTrain <- read.table("ozoneTrain.txt", header=TRUE)
summary(ozoneTrain[,c("maxO3","T12")])

plot(maxO3 ~ T12,data=ozoneTrain,pch=15,cex=0.5,col="blue")

reg.simple <- lm(maxO3 ~ T12, data = ozoneTrain)
summary(reg.simple)

res.stu <- rstudent(reg.simple)
plot(res.stu,pch=15,cex=0.5,ylab="Residuals",ylim=c(-3,3),col="blue")
abline(h=c(-2,0,2),lty=c(2,1,2),col="red")

plot(reg.simple$fitted.values,ozoneTrain[,"maxO3"])
abline(0,1)
