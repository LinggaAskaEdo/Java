package ws.zp.function;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class WSFunction
{
	public String generateToken()
	{
		UUID uuid = UUID.randomUUID();		
		return uuid.toString().substring(9, 17);
	}		
}