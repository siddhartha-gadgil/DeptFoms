import ammonite.ops._
import schedule._


val f = pwd / "data" / "faculty.yaml"

def writeFile(fac: Faculty) = {
  write.append(f, s"- name: ${fac.name}\n")
  write.append(f, s"  user-id: ${fac.username}\n")
  write.append(f, s"  position: \n")
  write.append(f, s"  research-areas: \n")
  write.append(f, s"  img-file: \n")
  write.append(f, s"  phone-ext: \n")
  write.append(f, s"\n")
}

def create() = Faculty.members.foreach(writeFile)
