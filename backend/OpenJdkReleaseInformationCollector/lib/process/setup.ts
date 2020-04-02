import * as childProcess from 'child_process';

export const buildJar = () => {
     // install package.json (production)
    childProcess.execSync('./gradlew --warning-mode all clean build', {
        cwd: `${process.cwd()}/lambda`,
        stdio: ['ignore', 'inherit', 'inherit'],
        env: { ...process.env },
        shell: 'bash'
    });
};